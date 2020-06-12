package com.project.bit.userStatus.service;

import com.project.bit.userStatus.domain.UserStatusVO;
import com.project.bit.userStatus.mapper.UserStatusMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserStatusServiceImpl implements UserStatusService {

    private UserStatusMapper userStatusMapper;


    @Override
    public List<UserStatusVO> getUserStatusAll() {
        return userStatusMapper.selectUserStatus();

    }
}
