package com.project.bit.admin.service;

import com.project.bit.admin.domain.UserDTO;
import com.project.bit.admin.mapper.UserSettingMapper;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@Service
@AllArgsConstructor
public class UserSettingServiceImpl implements UserSettingService{

    PasswordEncoder bCryptPasswordEncoder;
    UserSettingMapper userSettingMapper;

    //사용자 목록
    @Override
    public List<UserDTO> userSettingAll() {
        return userSettingMapper.selectUser();
    }

    //사용자 등록
    @Override
    public void regitUserSetting(UserDTO userDTO) {

        log.info("regit............."+userDTO);
        userSettingMapper.insertUser(userDTO);

    }

    //비밀번호 리셋
    @Override
    public void resetUserPw(UserDTO userDTO) {

        userDTO.setUserPw(bCryptPasswordEncoder.encode("1234"));
        userSettingMapper.resetUserPw(userDTO);
    }
}
