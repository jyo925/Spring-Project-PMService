package com.project.bit.userstatus.service;

import java.util.List;

import com.project.bit.approval.domain.Criteria;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bit.userstatus.domain.UserStatusVO;
import com.project.bit.userstatus.mapper.UserStatusMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserStatusServiceImpl implements UserStatusService {

    private UserStatusMapper userStatusMapper;

    //사용자현황 목록
    @Override
    public List<UserStatusVO> getUserStatusList(Criteria cri) {

        return userStatusMapper.selectUserStatus(cri);
    }

    //Total Count
    @Override
    public int countUsersStatusList() {

        return userStatusMapper.selectCountUserStatus();
    }
}
