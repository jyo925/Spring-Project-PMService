package com.project.bit.chat.service;

import com.project.bit.chat.domain.Message;

import java.util.Map;

public interface ChatService {

  Map<String, Object> initialConnection(String userId);

  boolean sendMessage(String roomNo, Message message);
  boolean joinMessage(Message message);


}
