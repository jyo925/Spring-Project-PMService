package com.project.bit.approval.service;

import com.project.bit.approval.domain.UserVO;
import com.project.bit.approval.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestMapper testMapper;

    @Override
    public List<UserVO> selectUser() {

        return testMapper.selectUser();
    }

    @Override
    public UserVO selectForm(String userId) {
        return testMapper.selectForm(userId);
    }
}
