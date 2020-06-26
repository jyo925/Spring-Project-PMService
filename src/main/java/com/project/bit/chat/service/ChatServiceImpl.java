package com.project.bit.chat.service;

import com.project.bit.chat.domain.ChatRoom;
import com.project.bit.chat.domain.Message;
import com.project.bit.chat.domain.MessageResponse;
import com.project.bit.chat.domain.Participation;
import com.project.bit.chat.mapper.ChatRoomMapper;
import com.project.bit.chat.mapper.MessageMapper;
import com.project.bit.chat.mapper.ParticipationMapper;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {

  private MessageMapper messageMapper;
  private ChatRoomMapper chatRoomMapper;
  private ParticipationMapper participationMapper;
  private SimpMessagingTemplate simpMessagingTemplate;

  public boolean joinWithNewRoom(Message message, String AuthorId) {
    ChatRoom chatRoom = new ChatRoom();
    chatRoomMapper.save(chatRoom);

    message.setRoomNo(chatRoom.getConversationId());
    message.setAuthorId(AuthorId);
    messageMapper.save(message);

    for ( Participation participation : message.getParticipations() ) {
      participation.setConversationId(chatRoom.getConversationId());
    }

    participating(message.getParticipations());
    return true;
  }

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

    simpMessagingTemplate.convertAndSend("/topic/room"+message.getRoomNo(), new MessageResponse(HtmlUtils.htmlEscape(message.getContent())) );
    return true;
  }

  @Override
  public boolean participating(List<Participation> participationList) {
    participationMapper.save(participationList);
    return true;
  }

  @Override
  public boolean inviteMessage(Message message) {
    ChatRoom chatRoom = new ChatRoom();
    chatRoomMapper.save(chatRoom);


    return false;
  }

  @Override
  public boolean sendMessage(String roomNo, Message message) {
    Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    switch (message.getType()) {
      case "NEWJOIN" : joinWithNewRoom(message, principal.getName());
                    break;
      case "JOIN" : joinMessage(message, principal);
                    break;
      case "SEND" : messageMapper.save(message);
                    simpMessagingTemplate.convertAndSend("/topic/room"+roomNo,
                    new MessageResponse(HtmlUtils.htmlEscape(message.getContent())));
                    break;
    }

    return true;
  }


}
