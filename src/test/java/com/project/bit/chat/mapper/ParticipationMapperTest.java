package com.project.bit.chat.mapper;

import com.project.bit.chat.domain.Participation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ParticipationMapperTest {

  @Autowired
  private ParticipationMapper participationMapper;

  @Test
  @DisplayName("사용자가 속한 채팅방 조회")
  public void test() {
    participationMapper.findByUserId("user001");
  }

  @Test
  @DisplayName("사용자가 처음 채팅방에 들어갔을시 참여 추가")
  public void test2() {
    participationMapper.save(new Participation("user002", "1" ));
  }
}