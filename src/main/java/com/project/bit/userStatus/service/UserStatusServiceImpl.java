package com.project.bit.userstatus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.bit.userstatus.domain.UserStatusVO;
import com.project.bit.userstatus.mapper.UserStatusMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserStatusServiceImpl implements UserStatusService {

    private UserStatusMapper userStatusMapper;


    @Override
    public List<UserStatusVO> getUserStatusAll() {
        return userStatusMapper.selectUserStatus();

    }
}
