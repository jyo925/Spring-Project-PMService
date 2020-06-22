package com.project.bit.approval.controller;

import com.project.bit.approval.domain.*;
import com.project.bit.approval.service.ApprovalDocService;
import com.project.bit.approval.service.ApprovalService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Log
@Controller
@RequestMapping("approval")
public class ApprovalController {

    @Autowired
    ApprovalDocService apDocService;
    @Autowired
    ApprovalService apService;

    //새 결재 작성하기
    @GetMapping("/apMain")
    public String apMain(Criteria cri,Principal principal, Model model) {
        //결재 문서별 개수 조회
        model.addAttribute("apDocCount", apDocService.getApDocCount(principal.getName(), cri));
        return "approval/apMain";
    }

    //새 결재 작성화면으로 이동
    @GetMapping("/goNewApDoc")
    public String goNewApDoc(@RequestParam("apFormNo") String apFormNo, Principal principal, Model model) {

        ////////////////////////////////////////////
        //PMO는 못하도록 -> 프로제트 내에서 상위 결재자 없음

        //문서양식 불러오기
        model.addAttribute("form", apDocService.getApForm(apFormNo));
        //문서양식에 맞는 & 사용자 직책 고려한 결재선
        model.addAttribute("approvers", apService.getApproverList(apFormNo, principal.getName()));

        //기안자 이름, 프로젝트 명 불러오기
        model.addAttribute("writer", apDocService.getApDocWriterInfo(principal.getName()));

        List<String> teamList = new ArrayList<>();//팀 목록
        List<ReferrerVO> referrerVO = apDocService.getReferrerUserList();//참조자목록
        for(int i=0; i<referrerVO.size(); i++){
            if (!teamList.contains(referrerVO.get(i).getTeamName())) {
                teamList.add(referrerVO.get(i).getTeamName());
            }
        }
        //참조자 리스트 정보 추가
        model.addAttribute("teams", teamList);
        model.addAttribute("referrers", referrerVO);

        return "approval/approvalNew";
    }

    //결재 요청(등록)
    @PostMapping("/postApDoc")
    public String postApDoc(ApDocDTO apDocDTO, ApFileDTO apFileDTO, Model model, Principal principal, String apReferrersId) {

        ////////////////////////////////////////////
        //임시서장 시 문서상태를 3으로 변경하여 첫번째 결재자가 결재하거나 결재리스트에 뜨는 일이 없도록

        //결재문서 등록
        log.info("새 결재 문서 등록 결과: " + apDocService.postApDoc(apDocDTO));

        //등록된 결재문서 번호 조회... -> 결재선, 참조자, 첨부파일 등록
        long apDocNo = apDocService.getNewApDocNo(apDocDTO);

        //결재선 정보 등록
        log.info("결재자 등록 수: " +
                apService.postApprovers(
                        apService.getApproverList("" + apDocDTO.getApFormNo(), principal.getName()), apDocNo));

        //첨부파일 등록하기
        if(!(apFileDTO.getApFileName()==null)) {
            apFileDTO.setApDocNo(apDocNo);
            apDocService.postApDocFiles(apFileDTO);
        }

        //참조자 등록
        log.info(apReferrersId);
        if(!apReferrersId.equals(" ")){
            apDocService.postApDocReferrers(apDocNo, apReferrersId);
        }
//        return "redirect:/approval/getApProgressList?type=N&keyword="+apDocNo;
        return "redirect:/approval/getApProgressList";
    }


    //결재 진행함 조회
    @GetMapping("/getApProgressList")
    public String getApProgressList(Criteria cri, Principal principal, Model model) {
        List<ApDocDTO> apProgressList = apDocService.getApProgressList(principal.getName(), cri);
        model.addAttribute("apProgressList", apProgressList);
        model.addAttribute("pageMaker", new PageDTO(cri, apDocService.getApDocCount(principal.getName(), cri).get(0)));
        return "approval/approvalProgress";
    }

    //결재 대기함 조회
    @GetMapping("/getApCheckList")
    public String getApCheckList(Criteria cri, Principal principal, Model model) {
        List<ApDocDTO> apCheckList = apDocService.getApCheckList(principal.getName(), cri);
        model.addAttribute("apCheckList", apCheckList);
        model.addAttribute("pageMaker", new PageDTO(cri, apDocService.getApDocCount(principal.getName(), cri).get(1)));
        return "approval/approvalCheck";
    }

    //임시저장함 조회
    @GetMapping("/getApTempList")
    public String getApTempList(Criteria cri, Principal principal, Model model) {
        return "approval/approvalTemp";
    }

    //참조문서함 조회
    @GetMapping("/getReferenceList")
    public String getReferenceList(Criteria cri, Principal principal, Model model){
        log.info("참조자 로그----------------------------------------------");
        List<ApDocDTO> apReferList = apDocService.getApReferList(principal.getName(),cri);
        model.addAttribute("apReferList", apReferList);
        model.addAttribute("pageMaker", new PageDTO(cri,apDocService.getApReferDocCount(principal.getName())));

        return "approval/approvalReference";
    }

    //결재완료함 조회
    @GetMapping("/getApEndList")
    public String getApEndList(Criteria cri, Principal principal, Model model){
        return "approval/apMain";
    }


    //결재문서 상세조회
    @GetMapping("/getApDoc")
    public String getApDoc(@RequestParam("apDocNo") String apDocNo, Model model,Principal principal) {

        //조회 권한 체크
        if(!apDocService.getApDocViewableUsers(apDocNo).contains(principal.getName())){
            return "redirect:apMain"; //임시로, 추후 안내페이지 이동하거나...
        }
        //해당 결재문서 데이터 조회
        ApDocDTO apDocData = apDocService.getApDoc(apDocNo);
        model.addAttribute("apDocData", apDocData);

        //결재문서에 대한 결재자 및 결재 정보 조회
        List<ApDTO> approvalData = apService.getApprovalList(apDocNo);
        model.addAttribute("approvalData", approvalData);

        //만약 조회자=결재자 ===> 승인/반려 처리할 수 있도록 따로 값을 추가로 전달
        for (ApDTO approver: approvalData
             ) {
            if((approver.getApApprover().equals(principal.getName())) &&
                    apDocData.getApDocStep()==approver.getApStep()
            ){
                model.addAttribute("checkApprover", true);
            }
        }

        //참조자 불러오기
        model.addAttribute("apReferrers", apDocService.getApDocReferrers(apDocNo));

        return "approval/approvalGet";
    }


    //승인 및 반려 처리
    @PostMapping("/postApproval")
    public String postApproval(Model model,Principal principal, ApDTO apDTO){

        //결재 정보 업데이트(결재 결과, 결재 의견, 결재 일자)
        apDTO.setApApprover(principal.getName());
        apService.putApproval(apDTO);

        //승인시
        if(apDTO.getApResult()=='1'){

            //마지막 결재자라면
            if(apService.getLastApprover(String.valueOf(apDTO.getApDocNo())).equals(principal.getName())){
                apDocService.putLastApDoc(apDTO.getApDocNo());
            }else {
                apDocService.putApDoc(apDTO);
            }
        //반려시
        }else {
            log.info("반려");
            // & 문서단계(0으로)업데이트...
        }
        return "redirect:/approval/getApCheckList"; //결재진행화면으로변경하기
    }

}
