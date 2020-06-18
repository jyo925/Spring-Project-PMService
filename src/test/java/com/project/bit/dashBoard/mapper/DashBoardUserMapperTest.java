package com.project.bit.dashBoard.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DashBoardUserMapperTest {
    @Autowired
    private DashBoardUserMapper dashBoardUserMapper;

    @Test
    public void test1() {
        dashBoardUserMapper.selectOutputCount("user001");
    }

    @Test
    public void test2() {
        dashBoardUserMapper.selectIssueStatus("user001");
    }

}