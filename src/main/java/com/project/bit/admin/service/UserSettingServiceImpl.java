package com.project.bit.admin.service;

import com.project.bit.admin.domain.UserVO;
import com.project.bit.admin.mapper.UserSettingMapper;
import com.project.bit.approval.domain.Criteria;
import com.project.bit.approval.domain.PageDTO;
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
    public List<UserVO> userSettingList(Criteria cri) {

        log.info(">>>>>>>>>"+cri);

        return userSettingMapper.selectUser(cri);
    }

    //Total Count
    @Override
    public int countUsersList( ) {

        return userSettingMapper.selectCountUsers();
    }

    //사용자 등록
    @Override
    public void regitUserSetting(UserVO userVO) {

        userVO.setUserPw(bCryptPasswordEncoder.encode("1234"));
        userSettingMapper.insertUser(userVO);

        log.info("regit............."+ userVO);
    }


    //사용자 삭제
    @Override
    public boolean removeUserSetting(String userId) {

      return userSettingMapper.deleteUser(userId)==1;
    }


    //비밀번호 리셋
    @Override
    public void resetUserPw(UserVO userVO) {

        userVO.setUserPw(bCryptPasswordEncoder.encode("1234"));
        userSettingMapper.resetUserPw(userVO);
    }
}
