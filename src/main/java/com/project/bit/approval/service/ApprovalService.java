package com.project.bit.approval.service;

import com.project.bit.approval.domain.ApDTO;
import com.project.bit.approval.domain.ApproverVO;

import java.util.List;

public interface ApprovalService {

    //결재 작성시 결재선 정보 셋팅
    public List<ApproverVO> getApproverList(String apFormNo, String userId);

    //결재자 정보 등록하기
    public int postApprovers(List<ApproverVO> approvers, Long apDocNo);
    
    //결재문서 결재 정보 불러오기
    public List<ApDTO> getApprovalList(String adDocNo);

    //결재 승인 반려시 정보 업데이트
    public int putApproval(ApDTO apDTO);

    //최종 결재자 여부
    public String getLastApprover(String adDocNo);

}
