package com.project.bit.chat.service;

import com.project.bit.chat.domain.ChatRoom;
import com.project.bit.chat.domain.Message;
import com.project.bit.chat.domain.MessageType;
import com.project.bit.chat.domain.Participation;
import com.project.bit.chat.mapper.ChatRoomMapper;
import com.project.bit.chat.mapper.MessageMapper;
import com.project.bit.chat.mapper.ParticipationMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {

  private MessageMapper messageMapper;
  private ChatRoomMapper chatRoomMapper;
  private ParticipationMapper participationMapper;

  @Override
  public Map<String, Object> initialConnection(String userId) {

    Map<String, Object> initialDataMap = new HashMap<>();
    initialDataMap.put("ChatRooms", messageMapper.findLastMessageByUserId(userId));

    return initialDataMap;
  }

  @Override
  public boolean joinMessage(Message message) {
    ChatRoom chatRoom = new ChatRoom();
    chatRoomMapper.save(chatRoom);

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    List<Participation> participations = new ArrayList<>();

    Participation host = Participation.builder()
      .conversationId(chatRoom.getConversationId())
      .userId(authentication.getName())
      .build();

    Participation guest = Participation.builder()
      .conversationId(chatRoom.getConversationId())
      .userId("뿌잉")
      .build();

    participations.add(host);
    participations.add(guest);

    return false;
  }

  @Override
  public boolean sendMessage(String roomNo, Message message) {
    switch (message.getType()) {
      case "ENTER" :
        break;

    }

    return false;
  }
}
