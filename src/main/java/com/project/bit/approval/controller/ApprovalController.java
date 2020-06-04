package com.project.bit.approval.controller;

import com.project.bit.approval.service.ApprovalDocService;
import com.project.bit.approval.service.ApprovalService;
import com.project.bit.approval.service.TestService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.function.Supplier;

@Log
@Controller
@RequestMapping("approval")
public class ApprovalController {

    @Autowired
    TestService testService;

    @Autowired
    ApprovalDocService apDocService;

    @Autowired
    ApprovalService approvalService;


    @GetMapping("/apMain")
    public String apMain(Principal principal, Model model) {
        return "approval/apMain";
    }
    
    //새 결재 작성하기
    @GetMapping("/goNewApDoc")
    public String goNewApDoc(@RequestParam("apFormNo") String apFormNo, Principal principal, Model model){
        //문서양식불러오기
        model.addAttribute("form", apDocService.getApForm(apFormNo));

        //문서양식에 맞는 & 사용자 직책 고려한 결재선 불러오기
        //결재자들의 아이디, 이름, 직책 정도?
        model.addAttribute("approvers", approvalService.getApproverList(apFormNo, "user007"));

        return "approval/approvalNew";

    }




    @GetMapping("/testApproval")
    public String testApproval(Principal principal, Model model) {

        testService.selectUser().forEach(userVO -> System.out.println(userVO.getId()));

        model.addAttribute("data", testService.selectForm("user03"));
        return "approval/ckeditor4copy";
    }



}
