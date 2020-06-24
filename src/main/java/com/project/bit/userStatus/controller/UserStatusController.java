package com.project.bit.userstatus.controller;

import com.project.bit.approval.domain.Criteria;
import com.project.bit.approval.domain.PageDTO;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.bit.userstatus.mapper.UserStatusMapper;
import com.project.bit.userstatus.service.UserStatusService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@Log
@Controller
@AllArgsConstructor
public class UserStatusController {

    private UserStatusService userStatusService;

    //사용자현황 목록
    @GetMapping("/userStatusList")
    public String getUserStatusList(Criteria cri, Model model) {

        model.addAttribute("userStatusList", userStatusService.getUserStatusList(cri));
        model.addAttribute("PageMaker", new PageDTO(cri,userStatusService.countUsersStatusList()));

        return "userStatus/userStatusList";

    }


}

