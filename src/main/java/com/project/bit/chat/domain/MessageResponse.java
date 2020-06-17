package com.project.bit.chat.domain;

import lombok.Getter;

@Getter
public class MessageResponse {

  private String content;

  public MessageResponse(String content) {
    this.content = content;
  }
}
