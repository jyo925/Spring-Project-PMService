package com.project.bit.userstatus.service;


import com.project.bit.userstatus.domain.UserStatusVO;
import com.project.bit.approval.domain.Criteria;

import java.util.List;

public interface UserStatusService {

    //사용자 목록
    List<UserStatusVO> getUserStatusList(Criteria cri);

    //사용자 Total Count
    int countUsersStatusList();


}

