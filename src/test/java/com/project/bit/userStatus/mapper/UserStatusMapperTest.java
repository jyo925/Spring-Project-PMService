package com.project.bit.userStatus.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

<<<<<<< HEAD
=======
import com.project.bit.userStatus.mapper.UserStatusMapper;

>>>>>>> 34869b6c4feeae05c9da20c2cc5a9d9e52d2a6ad
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

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