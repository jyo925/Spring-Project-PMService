package com.project.bit.approval.controller;

import com.project.bit.approval.domain.*;
import com.project.bit.approval.service.ApprovalDocService;
import com.project.bit.approval.service.ApprovalService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log
@Controller
@RequestMapping("approval")
public class ApprovalController {

    @Autowired
    ApprovalDocService apDocService;
    @Autowired
    ApprovalService apService;

    @GetMapping("/test")
    public String testap(){
        return "approval/indexapproval";
    }


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

        //팀 목록
        List<String> teamList = new ArrayList<>();
        List<ReferrerVO> referrerVO = apDocService.getReferrerUserList();
        for(int i=0; i<referrerVO.size(); i++){
            if (!teamList.contains(referrerVO.get(i).getTeamName())) {
                teamList.add(referrerVO.get(i).getTeamName());
            }
        }
        model.addAttribute("teams", teamList);
        //참조자 리스트 정보 추가
        model.addAttribute("referrers", referrerVO);
        return "approval/approvalNew";

    }

    //결재 요청(등록)
    @PostMapping("/postApDoc")
    public String postApDoc(ApDocDTO apDocDTO, ApFileDTO apFileDTO, Model model, Principal principal) {

        //결재문서 등록하기
        log.info("새 결재 문서 등록 결과: " + apDocService.postApDoc(apDocDTO));

        //등록된 결재문서번호 조회... -> 결재선, 참조자, 첨부파일 등록하기
        long apDocNo = apDocService.getNewApDocNo(apDocDTO);

        //결재선 정보 등록하기
        log.info("결재자 등록 수: " +
                apService.postApprovers(
                        apService.getApproverList("" + apDocDTO.getApFormNo(), principal.getName()), apDocNo));


        //첨부파일 등록하기
        //log.info("첨부파일 들어오는지? -----------------"+ apFileDTO);
        apFileDTO.setApDocNo(apDocNo);
        apDocService.postApDocFiles(apFileDTO);


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

    //임시저장함 조회
    @GetMapping("/getApTempList")
    public String getApTempList() {
        return "approval/approvalTemp";
    }

    //참조문서함 조회
    @GetMapping("/getReferenceList")
    public String getReferenceList(){
        return "approval/approvalReference";
    }





    //결재문서 상세조회
    @GetMapping("/getApDoc")
    public String getApDoc(@RequestParam("apDocNo") String apDocNo) {

        //조회 권한이 있는 사용자만 볼 수 있도록 체크

        //만약 결재자인 경우는 승인/반려 처리할 수 있도록 따로 값을 추가로 전달 model에

        //해당 결재문서 데이터 불러오기 ApDocDTO
        //결재문서에 대한 결재자 및 결재 정보 불러오기 ApDto


        return "approval/approvalGet";
    }


}
