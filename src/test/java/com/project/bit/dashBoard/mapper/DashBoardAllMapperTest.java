package com.project.bit.dashBoard.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DashBoardAllMapperTest {
    @Autowired
    private DashBoardAllMapper dashBoardAllMapper;

    @Test
    public void test1() {
        dashBoardAllMapper.selectKeyProject();
    }
}