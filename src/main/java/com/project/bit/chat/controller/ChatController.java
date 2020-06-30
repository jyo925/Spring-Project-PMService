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

  @GetMapping("/chat")
  public String chatPage() {
    return "chat/test";
  }

  @MessageMapping("/room/{room}")
  public void sendMessage(@DestinationVariable String room, @RequestBody Message message, Principal principal) {
    chatService.sendMessage(room, message, principal);
  }

  @MessageMapping("/chat/{type}")
  public void initialMessage(@DestinationVariable String type) {

  }


  @PostMapping("/test")
  @ResponseBody
  public ResponseEntity test(@RequestBody Message message) {
    log.info(message.toString());
    log.info(message.getParticipations().toString());
    return new ResponseEntity(HttpStatus.OK);
  }


  @GetMapping("/chat/initial")
  public ResponseEntity initialConnection(Principal principal) {
    return new ResponseEntity(chatService.initialConnection(principal.getName()), HttpStatus.OK);
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

  @GetMapping("/aaa")
  public ResponseEntity receive(@RequestBody ChatDTO chatDTO) {
    log.info(chatDTO.toString());
    return new ResponseEntity(HttpStatus.OK);
  }
}
