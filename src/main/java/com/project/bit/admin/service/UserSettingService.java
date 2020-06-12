package com.project.bit.admin.service;

import com.project.bit.admin.domain.UserDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public interface UserSettingService {


    void resetUserPw(UserDTO userDTO);




}
