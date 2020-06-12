package com.project.bit.admin.controller;

import com.project.bit.admin.domain.UserDTO;
import com.project.bit.admin.service.UserSettingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class UserSettingController {

    private UserSettingService userSettingService;

    @GetMapping("/userStatusList2")
    public String resetUserSettingPw(UserDTO userDTO){

        userSettingService.resetUserPw(userDTO);

        return "/userStatus/userStatusList";


    }
}
