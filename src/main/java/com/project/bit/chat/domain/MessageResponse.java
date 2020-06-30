package com.project.bit.chat.domain;

import com.project.bit.foo.domain.Users;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
public class MessageResponse {

  private List<Message> messageList;
  private List<Users> usersList;

  @Builder
  public MessageResponse(List<Message> messageList, List<Users> usersList) {
    this.messageList = messageList;
    this.usersList = usersList;
  }
}
