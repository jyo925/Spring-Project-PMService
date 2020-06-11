package com.project.bit.approval.controller;

import com.project.bit.approval.domain.ApDocDTO;
import com.project.bit.approval.service.ApprovalDocService;
import com.project.bit.approval.service.ApprovalService;
import com.project.bit.approval.service.TestService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    //새 결재 작성하기 클릭시
    @GetMapping("/apMain")
    public String apMain(Principal principal, Model model) {
        return "approval/apMain";
    }
    
    //새 결재 작성화면으로 이동
    @GetMapping("/goNewApDoc")
    public String goNewApDoc(@RequestParam("apFormNo") String apFormNo, Principal principal, Model model){
        //문서양식불러오기
        model.addAttribute("form", apDocService.getApForm(apFormNo));

        //문서양식에 맞는 & 사용자 직책 고려한 결재선 불러오기
        //결재자들의 아이디, 이름, 직책 정도?
        model.addAttribute("approvers", approvalService.getApproverList(apFormNo, "user007"));

        return "approval/approvalNew";

    }

    //결재 요청(등록)
    @PostMapping("/postApDoc")
    public String postApDoc(ApDocDTO apDocDTO, Model model, Principal principal){
        System.out.println("-------------------------------");
        log.info(apDocDTO.getApDocWriter());
        log.info(""+apDocDTO.getApFormNo());

        //결재문서 등록하기 insert
        log.info("등록 결과: "+ apDocService.postApDoc(apDocDTO));

        return "redirect:/approval/apMain"; //결재진행화면으로변경하기
    }

    //결재 진행함 클릭시
    @GetMapping("/getApProgressList")
    public String getApProgressList(Principal principal, Model model){
        //결재중인 문서 불러오기
        model.addAttribute("apProgressList", apDocService.getApProgressList("user007"));

        log.info(""+apDocService.getApProgressList("user007"));

        return "approval/approvalProgress";
    }

    //결재 대기 문서 클릭시
    @GetMapping("/getApCheckList")
    public String getApCheckList(){

        return "approval/approvalCheck";
    }

    //결재문서 상세조회
    @GetMapping("/getApDoc")
    public String getApDoc(@RequestParam("apDocNo") String apDocNo){

        //조회 권한이 있는 사용자만 볼 수 있도록 체크



        return null;
    }



}
