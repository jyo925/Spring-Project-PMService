package com.project.bit.chat.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ChatDTO {

  private Message message;
  private ChatRoom chatRoom;
  private List<Participation> participations;

  @Builder
  public ChatDTO(Message message, ChatRoom chatRoom) {
    this.message = message;
    this.chatRoom = chatRoom;
  }
}
