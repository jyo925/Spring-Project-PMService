package com.project.bit.chat.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Participation {
  private String userId;
  private String conversationId;
  private Date joinTime;

  public Participation(String userId, String conversationId) {
    this.userId = userId;
    this.conversationId = conversationId;
  }
}
