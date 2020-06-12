package com.project.bit.approval.controller;

import com.project.bit.approval.domain.ApDocDTO;
import com.project.bit.approval.domain.ApDocListVO;
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
import java.util.List;

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
    public String apMain(Criteria cri,Principal principal, Model model) {
        //결재 문서별 개수 불러오기
        model.addAttribute("apDocCount", apDocService.getApDocCount(principal.getName(), cri));
        return "approval/apMain";
    }

    //새 결재 작성으로 이동
    @GetMapping("/goNewApDoc")
    public String goNewApDoc(@RequestParam("apFormNo") String apFormNo, Principal principal, Model model) {
        //문서양식 불러오기
        model.addAttribute("form", apDocService.getApForm(apFormNo));

        //문서양식에 맞는 & 사용자 직책 고려한 결재선 불러오기
        model.addAttribute("approvers", apService.getApproverList(apFormNo, principal.getName()));

        return "approval/approvalNew";

    }

    //결재 요청(등록)
    @PostMapping("/postApDoc")
    public String postApDoc(ApDocDTO apDocDTO, Model model, Principal principal) {

        //결재문서 등록하기
        log.info("새 결재 문서 등록 결과: " + apDocService.postApDoc(apDocDTO));
        //등록된 결재문서번호 조회...
        long apDocNo = apDocService.getNewApDocNo(apDocDTO);
        //결재선 정보 등록하기
        log.info("결재자 등록 수: " +
                apService.postApprovers(
                        apService.getApproverList("" + apDocDTO.getApFormNo(), principal.getName()), apDocNo));

        return "redirect:/approval/apMain"; //결재진행화면으로변경하기
    }

    //결재 진행함 조회
    @GetMapping("/getApProgressList")
    public String getApProgressList(Criteria cri, Principal principal, Model model) {
        List<ApDocListVO> apProgressList = apDocService.getApProgressList(principal.getName(), cri);
        model.addAttribute("apProgressList", apProgressList);
        model.addAttribute("pageMaker", new PageDTO(cri, apDocService.getApDocCount(principal.getName(), cri).get(0)));
        return "approval/approvalProgress";
    }

    //결재 대기함 조회
    @GetMapping("/getApCheckList")
    public String getApCheckList(Criteria cri, Principal principal, Model model) {
        List<ApDocListVO> apCheckList = apDocService.getApCheckList(principal.getName(), cri);
        model.addAttribute("apCheckList", apCheckList);
        model.addAttribute("pageMaker", new PageDTO(cri, apDocService.getApDocCount(principal.getName(), cri).get(1)));
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
