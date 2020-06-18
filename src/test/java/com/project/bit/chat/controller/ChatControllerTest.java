package com.project.bit.chat.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class ChatControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("최초 커넥션 핸들러")
  @WithMockUser(username = "user001")
  public void test() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/chat/initial")
    ).andDo(print());
  }


}