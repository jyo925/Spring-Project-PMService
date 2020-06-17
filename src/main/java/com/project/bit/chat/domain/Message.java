package com.project.bit.chat.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Message {
  private int messageId;
  private String authorId;
  private String conversationId;
  private String content;
  private Date creationTime;
}
