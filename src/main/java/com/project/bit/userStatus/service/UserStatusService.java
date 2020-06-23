package com.project.bit.userStatus.service;

import com.project.bit.approval.domain.Criteria;
import com.project.bit.userStatus.domain.UserStatusVO;

import java.util.List;

public interface UserStatusService {

    //사용자 목록
    List<UserStatusVO> getUserStatusList(Criteria cri);

    //사용자 Total Count
    int countUsersStatusList();


}

