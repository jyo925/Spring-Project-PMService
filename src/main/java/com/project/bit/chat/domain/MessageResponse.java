package com.project.bit.chat.domain;

import com.project.bit.foo.domain.Users;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
public class MessageResponse {

  private String type;
  private List<Message> messageList;
  private List<Users> usersList;

  @Builder
  public MessageResponse(String type, List<Message> messageList, List<Users> usersList) {
    this.type = type;
    this.messageList = messageList;
    this.usersList = usersList;
  }
}
