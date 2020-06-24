package com.project.bit.admin.controller;

import com.project.bit.admin.domain.UserVO;
import com.project.bit.admin.service.UserSettingService;
import com.project.bit.approval.domain.Criteria;
import com.project.bit.approval.domain.PageDTO;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log
@Controller
@AllArgsConstructor
public class UserSettingController {

    private UserSettingService userSettingService;


    //사용자 리스트
    @GetMapping("/userSettingList")
    public String userSettingList(Criteria cri, Model model){

        model.addAttribute("userSettingList", userSettingService.userSettingList(cri));

        model.addAttribute("pageMaker", new PageDTO(cri,userSettingService.countUsersList()));

        return "admin/userSetting";
    }



    //사용자 등록
    @GetMapping("/regitUser")
    public String regitUserSetting(UserVO userVO) {

        userSettingService.regitUserSetting(userVO);

        return "redirect:/userSettingList";

    }

    //사용자 삭제
    @PostMapping("/deleteUser")
    public String removeUserSetting(@RequestParam("userId") String userId){

        log.info("Delete>>>>>>>>>>>>>>>"+userId);

        userSettingService.removeUserSetting(userId);

        return "redirect:/userSettingList";
    }


    //사용자 비밀번호 초기화
    @GetMapping("/userSettingResetPw")
    public String resetUserSettingPw(UserVO userVO){

        userSettingService.resetUserPw(userVO);

        return "redirect:/userSettingList";
    }
}
