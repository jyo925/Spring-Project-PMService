package com.project.bit.userStatus.mapper;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Log
@SpringBootTest
@AllArgsConstructor
class UserStatusMapperTest {


    private UserStatusMapper userStatusMapper;

    @Test
    public void testSelectList() {


        //userStatusMapper.selectUserAll().forEach(userStatusVO -> log.info(String.valueOf(userStatusVO)));



    }


}