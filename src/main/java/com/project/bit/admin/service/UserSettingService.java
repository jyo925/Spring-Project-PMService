package com.project.bit.admin.service;

import com.project.bit.admin.domain.UserVO;

import java.util.List;

public interface UserSettingService {

    //사용자목록
    List<UserVO> userSettingAll();

    //사용자 등록
    void regitUserSetting(UserVO userVO);

    //비밀번호 리셋
    void resetUserPw(UserVO userVO);




}
