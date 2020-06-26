package com.project.bit.chat.mapper;

import com.project.bit.chat.domain.Message;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
class MessageMapperTest {

  @Autowired
  private MessageMapper messageMapper;

  @Test
  @DisplayName("메세지 목록 전체 조회")
  public void findAll() {

    messageMapper.findAll();
  }

  @Test
  @DisplayName("사용자가 속한 채팅방별 마지막 메시지 조회")
  public void test2() {
    List<Message> messages = messageMapper.findLastMessageByUserId("user001");
    messages.forEach(message -> log.info(message+""));
  }

  @Test
  @DisplayName("메세지 저장")
  public void test3() {
    Message message = Message.builder()
      .authorId("user002")
      .content("안녕하세요3")
      .roomNo("1").build();

    messageMapper.save(message);
  }

  @Test
  @DisplayName("채팅방의 모든 메세지 조회")
  public void test4() {
    messageMapper.findByChatRoom("1");
  }
}
