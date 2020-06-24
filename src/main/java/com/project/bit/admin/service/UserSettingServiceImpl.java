package com.project.bit.admin.service;

import com.project.bit.admin.domain.UserVO;
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

    private PasswordEncoder bCryptPasswordEncoder;
    private UserSettingMapper userSettingMapper;

    //사용자 목록
    @Override
    public List<UserVO> userSettingAll() {
        return userSettingMapper.selectUser();
    }

    //사용자 등록
    @Override
    public void regitUserSetting(UserVO userVO) {

        log.info("regit............."+ userVO);
        userSettingMapper.insertUser(userVO);

    }

    //비밀번호 리셋
    @Override
    public void resetUserPw(UserVO userVO) {

        userVO.setUserPw(bCryptPasswordEncoder.encode("1234"));
        userSettingMapper.resetUserPw(userVO);
    }
}
