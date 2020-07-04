package com.project.bit.approval.service;

import com.project.bit.approval.domain.*;
import com.project.bit.foo.domain.event.Event;
import com.project.bit.foo.domain.event.EventGroup;

import java.util.List;

public interface ApprovalDocService {

    //결재문서 양식 조회
    public ApFormDTO getApForm(String apFormNo);

    //결재문서 등록
    public int postApDoc(ApDocDTO apDocDTO);
    
    //날짜 데이터 등록
    public void postApDocTerm(ApDateDTO apDateDTO);

    //날짜 데이터 조회
    public ApDateDTO getApDocTerm(String apDocNo);

    //결재문서 조회
    public ApDocDTO getApDoc(String apDocNo);

    //결재문서 조회 권한 있는 사용자 리스트
    public List<String> getApDocViewableUsers(String apDocNo);

    //문서 정보 업데이트
    public void putApDoc(ApDTO apDT);

    //문서 정보 최종 업데이트
    public void putLastApDoc(long apDocNo);

    //기안자 정보 불러오기
    public ApDocWriterVO getApDocWriterInfo(String userId);

    //등록한 결재문서 번호 조회
    public Long getNewApDocNo(ApDocDTO apDocDTO);

    //문서 삭제
    public void removeApDoc(String apDocNo, String apDocWriter);





    //문서 개수 조회 결재진행, 결재대기 순
    public List<Integer> getApDocCount(String apDocWriter);

    //참조문서 개수
    public int getApReferDocCount(String apReferrer);

    //결재완료문서 개수
    public int getApCompleteDocCount(String apDocWriter);



    //결재진행함 조회
    public List<ApDocDTO> getApProgressList(String apDocWriter, Criteria cri);
    
    //결재완료함 조회
    public List<ApDocDTO> getApCompleteList(String userId, Criteria cri);

    //결재 대기 문서함 조회
    public List<ApDocDTO> getApCheckList(String apDocWriter, Criteria cri);

    //참조문서함 조회
    public List<ApDocDTO> getApReferList(String apReferrer, Criteria cri);



    //첨부파일 등록하기
    public int postApDocFiles(ApFileDTO apFileDTO);

    //첨부파일 가져오기
    public List<ApFileDTO> getApFiles(String apDocNo);



    //참조자 조직도 리스트 불러오기
    public List<ReferrerVO> getReferrerUserList();

    //참조자 등록
    public void postApDocReferrers(long apDocNo, String apReferrersId);

    //참조자 목록 불러오기
    public String getApDocReferrers(String apDocNo);
    
    
    //이벤트 연동
    public Event getPostEvent(String apDocNo);
    //이벤트 멤버 조회
    public EventGroup getEventMemebers(String apDocNo);
};
