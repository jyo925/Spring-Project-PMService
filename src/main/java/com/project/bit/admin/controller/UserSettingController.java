package com.project.bit.admin.controller;

import com.project.bit.admin.domain.UserVO;
import com.project.bit.admin.service.UserSettingService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Log
@Controller
@AllArgsConstructor
public class UserSettingController {

    private UserSettingService userSettingService;


    //사용자 리스트
    @GetMapping("/userSetting")
    public String userSettingAll(Model model){

        model.addAttribute("userSettingAll", userSettingService.userSettingAll());

        return "admin/userSetting";
    }


    //사용자 등록
    @PostMapping("/regitUser")
    public String regitUserSetting(UserVO userVO) {

        userSettingService.regitUserSetting(userVO);

        return "redirect :/admin/userSetting";

    }




    //사용자 비밀번호 초기화
    @GetMapping("/userSettingResetPw")
    public String resetUserSettingPw(UserVO userVO){

        userSettingService.resetUserPw(userVO);

        return "/admin/userSetting";
    }
}
