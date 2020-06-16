package com.project.bit.admin.controller;

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
class UserSettingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void resetTest() throws Exception {
     mockMvc.perform(MockMvcRequestBuilders.get("/userSettingResetPw")
             .param("userId", "khj0623")
     )
     .andDo(print());
    }

}