package com.project.bit.approval.service;

import com.project.bit.approval.domain.ApDocDTO;
import com.project.bit.approval.domain.ApDocListVO;
import com.project.bit.approval.domain.ApFormDTO;
import com.project.bit.approval.domain.Criteria;

import java.util.List;

public interface ApprovalDocService {

    //결재문서 양식 조회
    public ApFormDTO getApForm(String apFormNo);
    //결재문서 등록
    public int postApDoc(ApDocDTO apDocDTO);

    //등록한 결재문서 번호 조회
    public Long getNewApDocNo(ApDocDTO apDocDTO);

    //결재진행함 조회
    public List<ApDocListVO> getApProgressList(String apDocWriter, Criteria cri);

    //문서 개수 조회 결재진행, 결재대기, 임시저장 순
    public List<Integer> getApDocCount(String apDocWriter, Criteria cri);

    //결재 대기 문서 조회
    public List<ApDocListVO> getApCheckList(String apDocWriter, Criteria cri);



}
