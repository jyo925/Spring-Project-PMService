package com.project.bit.approval.controller;

import com.project.bit.approval.domain.ApDocDTO;
import com.project.bit.approval.domain.Criteria;
import com.project.bit.approval.domain.PageDTO;
import com.project.bit.approval.service.ApprovalDocService;
import com.project.bit.approval.service.ApprovalService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Log
@Controller
@RequestMapping("approval")
public class ApprovalController {

    @Autowired
    ApprovalDocService apDocService;
    @Autowired
    ApprovalService apService;

    //새 결재 작성하기 클릭시
    @GetMapping("/apMain")
    public String apMain(Principal principal, Model model) {

        //결재 진행중, 대기, 임시저장 문서 불러와야함
        model.addAttribute("apDocCount", apDocService.getApDocCount(principal.getName()));


        return "approval/apMain";
    }

    //새 결재 작성화면으로 이동
    @GetMapping("/goNewApDoc")
    public String goNewApDoc(@RequestParam("apFormNo") String apFormNo, Principal principal, Model model) {
        //문서양식불러오기
        model.addAttribute("form", apDocService.getApForm(apFormNo));

        //문서양식에 맞는 & 사용자 직책 고려한 결재선 불러오기
        //결재자들의 아이디, 이름, 직책 정도?
        model.addAttribute("approvers", apService.getApproverList(apFormNo, principal.getName()));

        return "approval/approvalNew";

    }

    //결재 요청(등록)
    @PostMapping("/postApDoc")
    public String postApDoc(ApDocDTO apDocDTO, Model model, Principal principal) {

        //결재문서 등록하기
        log.info("새 결재 문서 등록 결과: " + apDocService.postApDoc(apDocDTO));
        //등록된 결재문서번호 조회...
        Long apDocNo = apDocService.getNewApDocNo(apDocDTO);
        //결재선 정보 등록하기
        log.info("결재자 등록 수: " +
                apService.postApprovers(
                        apService.getApproverList("" + apDocDTO.getApFormNo(), principal.getName()), apDocNo));

        return "redirect:/approval/apMain"; //결재진행화면으로변경하기
    }

    //결재 진행함 조회
    @GetMapping("/getApProgressList")
    public String getApProgressList(Criteria cri, Principal principal, Model model) {
        int total = 35; //리스트 개수 반환하는 서비스 로직 만들기
        //결재중인 문서 불러오기
        model.addAttribute("apProgressList", apDocService.getApProgressList(principal.getName(), cri));
        model.addAttribute("pageMaker", new PageDTO(cri, total));
        log.info("결재진행함----------------------------------------------------");
        log.info("" + cri);
        return "approval/approvalProgress";
    }

    //결재 대기 문서 클릭시
    @GetMapping("/getApCheckList")
    public String getApCheckList(Criteria cri, Principal principal, Model model) {
        int total = 35; //리스트 개수 반환하는 서비스 로직 만들기
        //내가 결재해야할 문서를 불러와야 함
        model.addAttribute("apCheckList", apDocService.getApCheckList(principal.getName(), cri));
        model.addAttribute("pageMaker", new PageDTO(cri, total));
        log.info("결재대기함---------------------------------------------------");

        return "approval/approvalCheck";
    }

    //임시저장함 클릭시
    @GetMapping("/getApTempList")
    public String getApTempList() {

        return "approval/approvalTemp";
    }

    @GetMapping("/getReferenceList")
    public String getReferenceList(){

        return "approval/approvalReference";
    }

    //결재문서 상세조회
    @GetMapping("/getApDoc")
    public String getApDoc(@RequestParam("apDocNo") String apDocNo) {

        //조회 권한이 있는 사용자만 볼 수 있도록 체크

        return null;
    }


}
