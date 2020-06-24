package com.project.bit.admin.service;

import com.project.bit.approval.domain.Criteria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserSettingServiceImplTest {

    @Autowired
    private UserSettingService userSettingService;

    @Test
    void userSettingList() {
        Criteria cri = new Criteria();
        userSettingService.userSettingList(cri);
    }
}