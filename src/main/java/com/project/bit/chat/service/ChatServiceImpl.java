package com.project.bit.chat.service;

import com.project.bit.chat.mapper.MessageMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {

  private MessageMapper messageMapper;

  @Override
  public Map<String, Object> initialConnection(String userId) {

    Map<String, Object> initialDataMap = new HashMap<>();
    initialDataMap.put("ChatRooms", messageMapper.findLastMessageByUserId(userId));

    return initialDataMap;
  }

}
