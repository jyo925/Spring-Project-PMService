package com.project.bit.chat.service;

import com.project.bit.chat.domain.ChatRoom;
import com.project.bit.chat.mapper.ChatRoomMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ChatServiceImplTest {

  @Autowired
  private ChatRoomMapper chatRoomMapper;

  @Test
  public void test() {
    ChatRoom chatRoom = new ChatRoom();
    chatRoomMapper.save(chatRoom);
    log.info(chatRoom.getConversationId());
  }
}