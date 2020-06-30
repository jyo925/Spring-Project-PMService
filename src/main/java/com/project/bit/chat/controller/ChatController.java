package com.project.bit.chat.controller;

import com.project.bit.chat.domain.ChatDTO;
import com.project.bit.chat.domain.Message;
import com.project.bit.chat.service.ChatService;
import com.project.bit.foo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@Slf4j
public class ChatController {

  private ChatService chatService;
  private UserService userService;
  private SimpMessagingTemplate simpMessagingTemplate;

  @MessageMapping("/room/{room}")
  public void sendMessage(@DestinationVariable String room, @RequestBody Message message, Principal principal) {
    chatService.sendMessage(room, message, principal);
  }

  @MessageMapping("/chat/{username}")
  public void initialMessage(@DestinationVariable String username) {
    simpMessagingTemplate.convertAndSend("/topic/chat/"+username, chatService.initialConnection(username));
  }

  @MessageMapping("/init/{username}")
  public void initConnection(@DestinationVariable String username) {
    simpMessagingTemplate.convertAndSend("/topic/init/"+username, chatService.initialConnection(username));
  }

  @PostMapping("/chat/invite")
  @ResponseBody
  public ResponseEntity fetchUserList(@RequestBody Message message) {
    if(message.getType().equals("NEWJOIN")) {
      return new ResponseEntity(chatService.inviteMessage(message), HttpStatus.OK);
    }
    return new ResponseEntity(HttpStatus.BAD_REQUEST);
  }

  @GetMapping("/chat/invite")
  @ResponseBody
  public ResponseEntity fetchUserList(Principal principal) {

    return new ResponseEntity(userService.selectAll().stream().filter( user ->
            !user.getUserId().equals(principal.getName())).collect(Collectors.toList())
            , HttpStatus.OK);
  }
}
