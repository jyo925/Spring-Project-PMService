package com.project.bit.userStatus.service;



import java.util.List;

import com.project.bit.approval.domain.Criteria;
import com.project.bit.userStatus.domain.UserStatusVO;

public interface UserStatusService {

    //사용자 목록
    List<UserStatusVO> getUserStatusList(Criteria cri);

    //사용자 Total Count
    int countUsersStatusList();


}

