package com.project.bit.chat.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Participation {
  private String userId;
  private String conversationId;
  private Date joinTime;

  @Builder
  public Participation(String userId, String conversationId) {
    this.userId = userId;
    this.conversationId = conversationId;
  }

  public Participation(String userId) {
    this.userId = userId;
  }
}
