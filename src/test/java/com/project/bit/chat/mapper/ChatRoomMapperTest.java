package com.project.bit.chat.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ChatRoomMapperTest {

  @Autowired
  private ChatRoomMapper chatRoomMapper;

  @Test
  @DisplayName("채팅방 개설")
  public void save() {
    for (int i = 0; i < 10; i++) {
      chatRoomMapper.save();
    }
  }
}
