package com.project.bit.admin.service;

import com.project.bit.admin.domain.UserDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public interface UserSettingService {

    //사용자목록
    List<UserDTO> userSettingAll();

    //사용자 등록
    void regitUserSetting(UserDTO userDTO);

    //비밀번호 리셋
    void resetUserPw(UserDTO userDTO);




}
