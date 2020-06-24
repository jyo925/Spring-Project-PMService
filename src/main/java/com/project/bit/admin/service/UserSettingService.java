package com.project.bit.admin.service;

import com.project.bit.admin.domain.UserVO;
import com.project.bit.approval.domain.Criteria;
import com.project.bit.approval.domain.PageDTO;

import java.util.List;

public interface UserSettingService {

    //사용자목록
    List<UserVO> userSettingList(Criteria cri);

    //사용자 Total Count
    int countUsersList();

    //사용자 등록
    void regitUserSetting(UserVO userVO);

    //사용자 삭제
    boolean removeUserSetting(String userId);

    //비밀번호 리셋
    void resetUserPw(UserVO userVO);




}
