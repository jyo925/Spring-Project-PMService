package com.project.bit.approval.controller;

import com.project.bit.approval.service.TestService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Log
@Controller
public class ApprovalController {

    @Autowired
    TestService testService;

    @GetMapping("/testApproval")
    public String testApproval(Principal principal, Model model) {

        testService.selectUser().forEach(userVO -> System.out.println(userVO.getId()));

        model.addAttribute("data", testService.selectForm("user02"));
        return "approval/ckeditor5New";
    }


}
