package com.project.bit.approval.mapper;

import com.project.bit.approval.domain.ApDTO;
import com.project.bit.approval.domain.ApPathDTO;
import com.project.bit.approval.domain.ApproverVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ApMapper {

    //결재선 가져오기
    public ApPathDTO selectApPath(String apFormNo);

    //결재선별 결재자 정보 가져오기 - 아이디, 이름, 직책
    public List<ApproverVO> selectApproverList(@Param("path") ApPathDTO path, String userId);

    //상위 결재자
    public ApproverVO selectSuperiorApprover(String userId);

    //결재자 등록하기
    public int insertApprover(ApDTO apDTO);

    public int updateApReceiveDate(ApDTO apDTO);

    //결재자 직책
    public String selectApproverDutyName(String userId);
    
    //결재중인 문서 결재 정보 가져오기
    public List<ApDTO> selectApprovalList(String apDocNo);

    //마지막 결재자 조회
    public String selectLastApprover(String apDocNo);

    //결재정보 업데이트
    public int updateApproval(ApDTO apDTO);

    //다음결재자 결재수신일 업데이트
    public int updateNextApReceiveDate(ApDTO apDTO);


}
