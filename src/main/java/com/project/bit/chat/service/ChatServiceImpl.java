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
        Message invi = inviteMessage(message, userId);
        simpMessagingTemplate.convertAndSend("/topic/init/" + userId, invi);
        List<Message> chatRooms = new ArrayList<>();
        chatRooms.add(invi);
        initialDataMap.put("ChatRooms", chatRooms);
        message.getParticipations().forEach( participation -> {
          simpMessagingTemplate.convertAndSend("/topic/init/"+participation.getUserId(), initialDataMap);
        });
        break;
      case "ENTER" : simpMessagingTemplate.convertAndSend("/topic/init/"+userId,
              new MessageResponse("ENTER",messageMapper.findByChatRoom(message.getRoomNo()), userMapper.findUsersByConversationId(message.getRoomNo())));
        break;
    }
  }

  @Override
  public void sendMessage(String roomNo, Message message, Principal principal) {
    message.setAuthorId(principal.getName());
    switch (message.getType()) {
      case "JOIN" : joinMessage(message, principal);
        break;
      case "SEND" : simpMessagingTemplate.convertAndSend("/topic/room/"+roomNo,
              sendProcess(message));
        break;
      case "LEAVE" : simpMessagingTemplate.convertAndSend("/topic/room/"+roomNo,
              leaveMessage(message, principal));
    }
  }

  public Message leaveMessage(Message message, Principal principal) {

    Message leaveMessage = Message.builder()
            .authorId(principal.getName())
            .content(principal.getName() + " 님이 채팅방에서 나가셨습니다.")
            .roomNo(message.getRoomNo())
            .build();

    messageMapper.save(leaveMessage);
    participationMapper.leave(leaveMessage);

    Message message1 = messageMapper.findByMessageId(leaveMessage.getMessageId());
    message1.setType("SEND");
    return message1;
  }
  /* 채팅방 초대 */
  public void joinMessage(Message message, Principal principal) {
    message.setRoomNo(message.getRoomNo());
    message.setAuthorId(principal.getName());
    messageMapper.save(message);

    for ( Participation participation : message.getParticipations() ) {
      participation.setConversationId(message.getRoomNo());
    }

    participating(message.getParticipations());
  }

  public boolean participating(List<Participation> participationList) {
    return participationMapper.save(participationList) == 1 ? true : false;
  }

  /* 채팅방 초대 메시지 */
  public Message inviteMessage(Message message, String userId) {
    try {
      Message inviteMessage = joinWithNewRoom(message, userId);
      return inviteMessage;
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
    Message foundMessage = messageMapper.findByMessageId(message.getMessageId());
    foundMessage.setType("SEND");
    return foundMessage;
  }

  private String inviteListMesage(List<Participation> participations) {

    String invitedList = "";
    for (Participation participation : participations ) {
      invitedList += participation.getUserId()+" ";
    }
    return invitedList;
  }

  public Message joinWithNewRoom(Message message, String authorId) {
    ChatRoom chatRoom = new ChatRoom();
    chatRoomMapper.save(chatRoom);

    Message inviteMessage = Message.builder()
            .authorId(authorId)
            .content(authorId+" 님이 "+ inviteListMesage(message.getParticipations()) + "을 초대하였습니다." )
            .roomNo(chatRoom.getConversationId())
            .build();

    messageMapper.save(inviteMessage);
    inviteMessage.setCreationTime(messageMapper.findByMessageId(inviteMessage.getMessageId()).getCreationTime());
    inviteMessage.setType("INVITE");

    List<Participation> participationList = message.getParticipations();
    participationList.add(new Participation(authorId));

    inviteMessage.setParticipations(participationList);

    for ( Participation participation : inviteMessage.getParticipations() ) {
      participation.setConversationId(chatRoom.getConversationId());
    }

    participating(participationList);
    return inviteMessage;
  }

  @Override
  public int[] count(String userId) {
    int[] countList = {userMapper.count(), participationMapper.count(userId)};
    return countList;
  }
}
