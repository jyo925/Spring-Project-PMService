package com.project.bit.chat.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoom {

  private String conversationId;
  private Date createdAt;


}
