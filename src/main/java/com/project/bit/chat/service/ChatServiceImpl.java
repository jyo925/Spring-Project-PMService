package com.project.bit.chat.service;

import com.project.bit.chat.domain.ChatRoom;
import com.project.bit.chat.domain.Message;
import com.project.bit.chat.domain.MessageResponse;
import com.project.bit.chat.domain.Participation;
import com.project.bit.chat.mapper.ChatRoomMapper;
import com.project.bit.chat.mapper.MessageMapper;
import com.project.bit.chat.mapper.ParticipationMapper;
import com.project.bit.foo.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {

  private MessageMapper messageMapper;
  private ChatRoomMapper chatRoomMapper;
  private UserMapper userMapper;
  private ParticipationMapper participationMapper;
  private SimpMessagingTemplate simpMessagingTemplate;


  /* 처음 접속시 채팅방 리스트 */
  @Override
  public Map<String, Object> initialConnection(String userId) {

    Map<String, Object> initialDataMap = new HashMap<>();
    initialDataMap.put("ChatRooms", messageMapper.findLastMessageByUserId(userId));
    return initialDataMap;
  }

  @Override
  public boolean joinMessage(Message message, Principal principal) {

    message.setRoomNo(message.getRoomNo());
    message.setAuthorId(principal.getName());
    messageMapper.save(message);

    for ( Participation participation : message.getParticipations() ) {
      participation.setConversationId(message.getRoomNo());
    }

    participating(message.getParticipations());

    /*simpMessagingTemplate.convertAndSend("/topic/room"+message.getRoomNo(), new MessageResponse(HtmlUtils.htmlEscape(message.getContent())) );*/
    return true;
  }

  @Override
  public boolean participating(List<Participation> participationList) {
    participationMapper.save(participationList);
    return true;
  }

  /* 채팅방 초대 메시지 */
  @Override
  public Message inviteMessage(Message message) {
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    try {
      joinWithNewRoom(message, userDetails.getUsername());
    } catch (Exception e) {
      log.error("fail to create ChatRooms");
    }

    return message;
  }

  @Override
  public boolean sendMessage(String roomNo, Message message, Principal principal) {
    message.setAuthorId(principal.getName());
    switch (message.getType()) {
      case "NEWJOIN" : joinWithNewRoom(message, principal.getName());
                    break;
      case "JOIN" : joinMessage(message, principal);
                    break;
      case "ENTER" : simpMessagingTemplate.convertAndSend("/topic/room/"+roomNo,
        new MessageResponse(messageMapper.findByChatRoom(roomNo), userMapper.findUsersByConversationId(roomNo)));
                    break;
      case "SEND" : simpMessagingTemplate.convertAndSend("/topic/room/"+roomNo,
                    sendProcess(message));
                    break;
    }
    return true;
  }

  /* @params
     author_id, conversation_id, content
  */
  public Message sendProcess(Message message) {
    messageMapper.save(message);
    return messageMapper.findByMessageId(message.getMessageId());
  }

  public Message joinWithNewRoom(Message message, String AuthorId) {
    ChatRoom chatRoom = new ChatRoom();
    chatRoomMapper.save(chatRoom);

    message.setRoomNo(chatRoom.getConversationId());
    message.setAuthorId(AuthorId);
    messageMapper.save(message);

    message.getParticipations().add(new Participation(AuthorId));

    for ( Participation participation : message.getParticipations() ) {
      participation.setConversationId(chatRoom.getConversationId());
    }

    participating(message.getParticipations());
    return message;
  }

}
