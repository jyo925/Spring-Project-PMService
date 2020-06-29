package com.project.bit.userStatus.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.project.bit.userStatus.service.UserStatusService;

@SpringBootTest
public class UserStatusServiceTest {

    @Autowired
    private UserStatusService userStatusService;

    @Test
    public void getUserAll() {

        //userStatusService.getUserAll();

    }

    @Test
    public void getTaskStatusCount() {
        //userStatusService.getTaskStatusCount("user004");

    }


}