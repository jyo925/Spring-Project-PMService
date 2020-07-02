package com.project.bit.chat.service;

import com.project.bit.chat.domain.ChatRoom;
import com.project.bit.chat.domain.Message;
import com.project.bit.chat.domain.MessageResponse;
import com.project.bit.chat.domain.Participation;
import com.project.bit.chat.mapper.ChatRoomMapper;
import com.project.bit.chat.mapper.MessageMapper;
import com.project.bit.chat.mapper.ParticipationMapper;
import com.project.bit.foo.mapper.UserMapper;
import com.project.bit.foo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ChatServiceImpl implements ChatService {
  private MessageMapper messageMapper;

  private ChatRoomMapper chatRoomMapper;
  private UserMapper userMapper;
  private ParticipationMapper participationMapper;
  private SimpMessagingTemplate simpMessagingTemplate;
  private UserService userService;


  /* 처음 접속시 채팅방 리스트 */
  @Override
  public void initialConnection(String userId, Message message) {
    Map<String, Object> initialDataMap = new HashMap<>();
    switch (message.getType()) {
      case "FETCHUSERLIST":
        initialDataMap.put("UserList",userService.selectAll().stream().filter( user ->
                !user.getUserId().equals(userId)).collect(Collectors.toList()));
        simpMessagingTemplate.convertAndSend("/topic/init/" + userId, initialDataMap);
        break;
      case "FETCHCHATROOMLIST":
        initialDataMap.put("ChatRooms", messageMapper.findLastMessageByUserId(userId));
        simpMessagingTemplate.convertAndSend("/topic/init/" + userId, initialDataMap);
        break;
      case "INVITE":
        simpMessagingTemplate.convertAndSend("/topic/init/" + userId, inviteMessage(message, userId));
        List<Message> chatRooms = new ArrayList<>();
        chatRooms.add(message);
        initialDataMap.put("ChatRooms", chatRooms);
        message.getParticipations().forEach( participation -> {
          simpMessagingTemplate.convertAndSend("/topic/init/"+participation.getUserId(), initialDataMap);
        });
        break;
    }
  }

  @Override
  public void sendMessage(String roomNo, Message message, Principal principal) {
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
  }

  /* 채팅방 초대 */
  public boolean joinMessage(Message message, Principal principal) {

    message.setRoomNo(message.getRoomNo());
    message.setAuthorId(principal.getName());
    messageMapper.save(message);

    for ( Participation participation : message.getParticipations() ) {
      participation.setConversationId(message.getRoomNo());
    }

    participating(message.getParticipations());
    return true;
  }

  public boolean participating(List<Participation> participationList) {
    participationMapper.save(participationList);
    return true;
  }

  /* 채팅방 초대 메시지 */
  public Message inviteMessage(Message message, String userId) {
    try {
      joinWithNewRoom(message, userId);
    } catch (Exception e) {
      log.error("fail to create ChatRooms");
    }

    return message;
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
