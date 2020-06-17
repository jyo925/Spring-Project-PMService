package com.project.bit.chat.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Note {

  private int noteId;
  private String authorId;
  private String receiverId;
  private String content;
  private Date creationTime;
}
