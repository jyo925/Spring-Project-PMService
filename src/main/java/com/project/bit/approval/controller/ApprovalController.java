package com.project.bit.approval.controller;

import com.project.bit.approval.domain.*;
import com.project.bit.approval.service.ApprovalDocService;
import com.project.bit.approval.service.ApprovalService;
import com.project.bit.foo.domain.event.Event;
import com.project.bit.foo.domain.event.EventGroup;
import com.project.bit.foo.service.EventService.EventGroupService;
import com.project.bit.foo.service.EventService.EventService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @Autowired
    EventService eventService;
    @Autowired
    EventGroupService eventGroupService;


    public boolean postEvent(String apDocNo){

        //문서번호를 이용하여 Event 등록해야하는 결재인지 확인 (문서 종류가 1,2,3인 경우만)
        //apDocServie.getPostEventCheck(apDocNo);
        //return 값으로 문서 종류 -->ex : username 휴가 , 이찬영 출장, 정상구 교육

        //event 제목: 이찬영 PM 출장
        //시작일, 종료일+1 해줘야함
        //        eventService.insertEvent(event);
        
        //같은 프로젝트 멤버 아이디 eventgroup에 셋팅해야함
        //        eventGroupService.insertMember(eventGroup, event);
        
        return false;
    };



    @GetMapping("/apTest")
    public String apTest(){
        return "approval/apTest";
    }

    @GetMapping("/apMain")
    public String apMain(Criteria cri, Principal principal, Model model) {
        model.addAttribute("apDocCount", apDocService.getApDocCount(principal.getName()));
        return "approval/apMain";
    }

    //새 결재 작성 화면
    @GetMapping("/goNewApDoc")
    public String goNewApDoc(String apFormNo, Principal principal, Model model, RedirectAttributes rttr) {

        List<ApproverVO> approvers = apService.getApproverList(apFormNo, principal.getName());
        if(approvers.get(0) == null){
            rttr.addFlashAttribute("notApprovers", "상위 결재자가 없습니다. Admin에게 문의 바랍니다.");
            return "redirect:/approval/apMain";
        }
        model.addAttribute("approvers", approvers);
        model.addAttribute("form", apDocService.getApForm(apFormNo));
        model.addAttribute("writer", apDocService.getApDocWriterInfo(principal.getName()));

        List<String> teamList = new ArrayList<>();
        List<ReferrerVO> referrerVO = apDocService.getReferrerUserList();
        for (int i = 0; i < referrerVO.size(); i++) {
            if (!teamList.contains(referrerVO.get(i).getTeamName())) {
                teamList.add(referrerVO.get(i).getTeamName());
            }
        }
        model.addAttribute("teams", teamList);
        model.addAttribute("referrers", referrerVO);

        return "approval/approvalNew";
    }

    //결재 요청
    @PostMapping("/postApDoc")
    public String postApDoc(ApDocDTO apDocDTO, ApFileDTO apFileDTO,
                            Model model, Principal principal, String apReferrersId, ApDateDTO apDateDTO) {

        apDocService.postApDoc(apDocDTO);

        long apDocNo = apDocService.getNewApDocNo(apDocDTO);

        apService.postApprovers(
            apService.getApproverList("" + apDocDTO.getApFormNo(), principal.getName()), apDocNo);

        if (!(apFileDTO.getApFileName() == null)) {
            apFileDTO.setApDocNo(apDocNo);
            apDocService.postApDocFiles(apFileDTO);
        }
        if (!apReferrersId.equals(" ")) {
            apDocService.postApDocReferrers(apDocNo, apReferrersId);
        }
        if(apDateDTO.getApStartDate() != null && apDateDTO.getApEndDate() != null){
            apDateDTO.setApDocNo(apDocNo);
            apDocService.postApDocTerm(apDateDTO);
        }

        return "redirect:/approval/apMain";
    }

    //결재 진행함 조회
    @GetMapping("/getApProgressList")
    public String getApProgressList(Criteria cri, Principal principal, Model model) {
        model.addAttribute("apDocListTypeName", "결재 진행함");
        model.addAttribute("apDocList", apDocService.getApProgressList(principal.getName(), cri));
        model.addAttribute("pageMaker", new PageDTO(cri, apDocService.getApDocCount(principal.getName()).get(0)));
        return "approval/approvalDocList_1";
    }

    //결재 대기함 조회
    @GetMapping("/getApCheckList")
    public String getApCheckList(Criteria cri, Principal principal, Model model) {
        model.addAttribute("apDocListTypeName", "결재 대기 문서");
        model.addAttribute("apDocList", apDocService.getApCheckList(principal.getName(), cri));
        model.addAttribute("pageMaker", new PageDTO(cri, apDocService.getApDocCount(principal.getName()).get(1)));
        return "approval/approvalDocList_1";
    }

    //참조문서함 조회
    @GetMapping("/getReferenceList")
    public String getReferenceList(Criteria cri, Principal principal, Model model) {
        model.addAttribute("apDocListTypeName", "참조 문서함");
        model.addAttribute("apDocList", apDocService.getApReferList(principal.getName(), cri));
        model.addAttribute("pageMaker", new PageDTO(cri, apDocService.getApReferDocCount(principal.getName())));
        return "approval/approvalDocList_2";
    }

    //결재완료함 조회
    @GetMapping("/getApEndList")
    public String getApEndList(Criteria cri, Principal principal, Model model) {
        model.addAttribute("apDocListTypeName", "결재 완료함");
        model.addAttribute("apDocList", apDocService.getApCompleteList(principal.getName(), cri));
        model.addAttribute("pageMaker", new PageDTO(cri, apDocService.getApCompleteDocCount(principal.getName())));
        return "approval/approvalDocList_2";
    }

    //결재문서 상세조회
    @GetMapping("/getApDoc")
    public String getApDoc(String apDocNo, Model model, Principal principal) {

        //조회 권한 체크
        if (!apDocService.getApDocViewableUsers(apDocNo).contains(principal.getName())) {
            return "redirect:apMain"; //임시로, 추후 안내페이지 이동하거나...///////////////////////
        }
        ApDocDTO apDocData = apDocService.getApDoc(apDocNo);
        model.addAttribute("apDocData", apDocData);

        List<ApDTO> approvalData = apService.getApprovalList(apDocNo);
        model.addAttribute("approvalData", approvalData);

        //만약 조회자=결재자 ===> 승인/반려 처리할 수 있도록 따로 값을 추가로 전달
        for (ApDTO approver : approvalData){
            if ((approver.getApApprover().equals(principal.getName())) &&
                    apDocData.getApDocStep() == approver.getApStep()){
                model.addAttribute("checkApprover", true);
            }
        }
        model.addAttribute("apReferrers", apDocService.getApDocReferrers(apDocNo));
        
        long formNo = apDocData.getApFormNo();
        if(formNo == 1 || formNo == 2 || formNo == 3){
            //날짜 데이터 가져오기
            model.addAttribute("apDocTerm", apDocService.getApDocTerm(apDocNo));
        }
        return "approval/approvalGet";
    }


    //승인 및 반려 처리
    @PostMapping("/postApproval")
    public String postApproval(Model model, Principal principal, ApDTO apDTO) {
        String approver = principal.getName();

        apDTO.setApApprover(approver);
        apService.putApproval(apDTO);

        //마지막 결재자가 승인한경우
        if (apService.getLastApprover(String.valueOf(apDTO.getApDocNo())).equals(approver)
                && apDTO.getApResult() == '1') {
            apDocService.putLastApDoc(apDTO.getApDocNo());
            //이벤트 등록 처리 메소드로 처리
            
        } else {
            apDocService.putApDoc(apDTO);
        }
        return "redirect:/approval/getApCheckList";
    }
    
    //문서 삭제 처리
    @PostMapping("/removeApproval")
    public String removeApproval(Principal principal, String apDelete, String apDocNo){
        apDocService.removeApDoc(apDocNo, principal.getName());
        return "redirect:/approval/apMain";
    }
}
