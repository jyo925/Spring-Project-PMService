package com.project.bit.chat.service;

import com.project.bit.chat.domain.Message;
import com.project.bit.chat.domain.Participation;

import java.security.Principal;
import java.util.List;
import java.util.Map;

public interface ChatService {

  Map<String, Object> initialConnection(String userId);

  boolean sendMessage(String roomNo, Message message);
  boolean joinMessage(Message message, Principal principal);
  boolean participating(List<Participation> participationList);
  boolean inviteMessage(Message message);

}
