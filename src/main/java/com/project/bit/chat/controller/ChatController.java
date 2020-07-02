package com.project.bit.chat.controller;

import com.project.bit.chat.domain.Message;
import com.project.bit.chat.service.ChatService;
import com.project.bit.foo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Controller
@AllArgsConstructor
@Slf4j
public class ChatController {

  private ChatService chatService;
  private UserService userService;

  @MessageMapping("/room/{room}")
  public void sendMessage(@DestinationVariable String room, @RequestBody Message message, Principal principal) {
    chatService.sendMessage(room, message, principal);
  }

  @MessageMapping("/init/{username}")
  public void initConnection(@DestinationVariable String username, @RequestBody Message message) {
    chatService.initialConnection(username, message);
  }

  @GetMapping("/chat")
  public String chatPage() {
    return "/chat/chat";
  }

}
