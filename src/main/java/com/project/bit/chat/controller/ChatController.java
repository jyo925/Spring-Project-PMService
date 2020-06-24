package com.project.bit.chat.controller;

import com.project.bit.chat.domain.ChatDTO;
import com.project.bit.chat.domain.Message;
import com.project.bit.chat.service.ChatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
@AllArgsConstructor
@Slf4j
public class ChatController {

  private SimpMessagingTemplate simpMessagingTemplate;
  private ChatService chatService;

  @GetMapping("/chat")
  public String chatPage() {
    return "chat/test";
  }

  @MessageMapping("/room/{room}")
  public void sendMessage(@DestinationVariable String roomNo, Message message) {
    simpMessagingTemplate.convertAndSend("/topic/room/"+roomNo, HtmlUtils.htmlEscape(message.getContent()));
  }

  @GetMapping("/chat/initial")
  public ResponseEntity initialConnection(Principal principal) {
    return new ResponseEntity(chatService.initialConnection(principal.getName()), HttpStatus.OK);
  }


  @GetMapping("/aaa")
  public ResponseEntity receive(@RequestBody ChatDTO chatDTO) {
    log.info(chatDTO.toString());
    return new ResponseEntity(HttpStatus.OK);
  }
}
