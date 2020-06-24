package com.project.bit.admin.mapper;

import com.project.bit.admin.domain.UserVO;
import com.project.bit.approval.domain.Criteria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserSettingMapperTest {

    @Autowired
    UserSettingMapper userSettingMapper;

    @Test
    void selectUser() {
        Criteria criteria = new Criteria();
        criteria.setPageNum(2);
        criteria.setAmount(10);

        userSettingMapper.selectUser(criteria);

    }
}