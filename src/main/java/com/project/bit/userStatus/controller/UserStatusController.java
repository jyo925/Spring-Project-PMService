package com.project.bit.userStatus.controller;

import com.project.bit.userStatus.mapper.UserStatusMapper;
import com.project.bit.userStatus.service.UserStatusService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log
@Controller
@AllArgsConstructor
public class UserStatusController {

    private UserStatusService userStatusService;
    private UserStatusMapper userStatusMapper;

    @GetMapping("/userStatusList")
    public void getUserStatusAll(Model model) {

        log.info("All Status List");
        System.out.println(userStatusMapper.selectUserStatus());

        model.addAttribute("userStatusList", userStatusService.getUserStatusAll());

    }


}
