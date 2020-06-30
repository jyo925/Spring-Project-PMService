package com.project.bit.userStatus.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.bit.userStatus.mapper.UserStatusMapper;
<<<<<<< HEAD

=======
>>>>>>> ebb6baa905f6c30fe3c35231d31280b77daf82c1
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