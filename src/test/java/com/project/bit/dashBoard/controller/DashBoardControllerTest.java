package com.project.bit.dashBoard.controller;

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
class DashBoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test() throws Exception {
        mockMvc.perform((MockMvcRequestBuilders.get("/dashBoardAll")
        )).andDo(print());
    }

    @Test
    @WithMockUser
    public void test2() throws Exception {
        mockMvc.perform((MockMvcRequestBuilders.get("/dIndex"))).andDo(print());
    }

    @Test
    @WithMockUser
    public void test3() throws Exception {
        mockMvc.perform((MockMvcRequestBuilders.get("/monthly"))).andDo(print());
    }

}