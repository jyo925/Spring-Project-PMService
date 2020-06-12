package com.project.bit.admin.service;

import com.project.bit.admin.domain.UserDTO;
import com.project.bit.admin.mapper.UserSettingMapper;
import com.project.bit.foo.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserSettingServiceImpl implements UserSettingService{

    PasswordEncoder bCryptPasswordEncoder;
    UserSettingMapper userSettingMapper;

    @Override
    public void resetUserPw(UserDTO userDTO) {

        userDTO.setUserPw(bCryptPasswordEncoder.encode("1234"));
        userSettingMapper.resetUserPw(userDTO);
    }
}
