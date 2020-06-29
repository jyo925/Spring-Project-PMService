package com.project.bit.userStatus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.bit.approval.domain.Criteria;
import com.project.bit.userStatus.domain.UserStatusVO;
import com.project.bit.userStatus.mapper.UserStatusMapper;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@Log
@Service
@AllArgsConstructor
public class UserStatusServiceImpl implements UserStatusService {

    private UserStatusMapper userStatusMapper;

    //사용자현황 목록
    @Override
    public List<UserStatusVO> getUserStatusList(Criteria cri) {

        log.info(">>>>>>>>>>>>>>>>>>>>>>"+cri);

        return userStatusMapper.selectUserStatus(cri);
    }

    //Total Count
    @Override
    public int countUsersStatusList() {

        return userStatusMapper.selectCountUserStatus();
    }
}
