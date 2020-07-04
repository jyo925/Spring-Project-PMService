package com.project.bit.chat.service;

import com.project.bit.chat.domain.Message;
import com.project.bit.chat.domain.Participation;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.List;
import java.util.Map;

public interface ChatService {

  void initialConnection(String userId, Message message);
  void sendMessage(String roomNo, Message message, Principal principal);
  int[] count(String userId);
}
