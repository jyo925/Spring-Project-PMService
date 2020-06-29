package com.project.bit.chat.mapper;

import com.project.bit.chat.domain.ChatRoom;
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
    ChatRoom chatRoom = new ChatRoom();
    chatRoomMapper.save(chatRoom);
    log.info("뿌이이잉" + chatRoom.getConversationId());
  }
}
