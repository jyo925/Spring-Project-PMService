package com.project.bit.userStatus.service;

import com.project.bit.approval.domain.Criteria;
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
