package com.project.bit.approval.mapper;

import com.project.bit.approval.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ApDocMapper {

    public ApFormDTO selectApForm(String apFormNo);

    public int insertApDoc(ApDocDTO apDocDTO);

    public List<ApDocDTO> selectApProgressList(String apDocWriter, Criteria cri);

    public List<ApDocDTO> selectApCompleteList(String userId, Criteria cri);

    public List<ApDocDTO> selectApCheckList(String apDocWriter, Criteria cri);

    public List<ApDocDTO> selectApReferList(String apReferrer, Criteria cri);

    public Long selectNewApDocNo(ApDocDTO apDocDTO);

    //결재진행문서 개수
    public int selectCountApDoc(int apDocStatus, String apDocWriter);

    //결재대기문서 개수
    public int selectCountApCheck(String apDocWriter);

    //참조문서 개수
    public int selectCountApRefer(String apReferrer);


    //결재문서 조회
    public ApDocDTO selectApDoc(String apDocNo);

    //조회 가능한 사용자 리스트
    public List<String> selectApDocViewableUsers(String apDocNo);

    //문서 단계 업데이트
    public void updateApDocStep(long apDocNo);

    //결재 완료시 문서 업데이트
    public void updateLastApDoc(long apDocNo);


    //기안자 명, 기안프로젝트 명 조회
    public ApDocWriterVO selectApDocWriterInfo(String userId);
    
    //반려시 
    public int updateApDocReject(long apDocNo);
    
    //문서 삭제
    public void deleteApDoc(String apDocNo, String apDocWriter);

    //기간 등록 (휴가 or 출장 ...)
    public void insertApDocTerm(ApDateDTO apDateDTO);

    //기간 조회
    public ApDateDTO selectApDocTerm(String apDocNo);


    //이벤트 연동
    public ApEventVO selectApEvent(String apDocNo);

    //이벤트 유저 조회
    public String selectApEventUserId(String apDocNo);

    //이벤트 멤버 조회
    public List<String> selectApEventMembers(String apDocNo);

}
