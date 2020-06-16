package com.project.bit.admin.controller;

import com.project.bit.admin.domain.UserDTO;
import com.project.bit.admin.service.UserSettingService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log
@Controller
@AllArgsConstructor
public class UserSettingController {

    private UserSettingService userSettingService;


    //사용자 목록
    @GetMapping("/userSetting")
    public void userSettingAll(Model model){

        model.addAttribute("userSettingAll", userSettingService.userSettingAll());

    }

    //사용자 등록
    @GetMapping("/regitUser")
    public String regitUserSetting(UserDTO userDTO) {

        userSettingService.regitUserSetting(userDTO);

        return "redirect :/admin/userSetting";

    }




    //사용자 비밀번호 초기화
    @GetMapping("/userSettingResetPw")
    public String resetUserSettingPw(UserDTO userDTO){

        userSettingService.resetUserPw(userDTO);

        return "/admin/userSetting";
    }
}
