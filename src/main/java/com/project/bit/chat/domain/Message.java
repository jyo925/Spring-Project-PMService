package com.project.bit.chat.domain;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Message {
  private String YN;
  private String type;
  private int messageId;
  private String authorId;
  private String roomNo;
  private String content;
  private Date creationTime;
  private List<Participation> participations;

  @Builder
  public Message(String authorId, String roomNo, String content) {
    this.authorId = authorId;
    this.roomNo = roomNo;
    this.content = content;
  }

  @Builder
  public Message(String type, int messageId, String authorId, String roomNo, String content, Date creationTime, List<Participation> participations) {
    this.type = type;
    this.messageId = messageId;
    this.authorId = authorId;
    this.roomNo = roomNo;
    this.content = content;
    this.creationTime = creationTime;
    this.participations = participations;
  }
}
