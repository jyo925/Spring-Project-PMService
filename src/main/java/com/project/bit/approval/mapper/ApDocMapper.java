package com.project.bit.approval.mapper;

import com.project.bit.approval.domain.ApDocDTO;
import com.project.bit.approval.domain.ApDocListVO;
import com.project.bit.approval.domain.ApFormDTO;
import com.project.bit.approval.domain.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ApDocMapper {

    public ApFormDTO selectApForm(String apFormNo);

    public int insertApDoc(ApDocDTO apDocDTO);

    //결재 진행함 조회
    public List<ApDocListVO> selectApProgressList(String apDocWriter, Criteria cri);

    //결재 대기 문서함 조회
    public List<ApDocListVO> selectApCheckList(String apDocWriter, Criteria cri);

    public Long selectNewApDocNo(ApDocDTO apDocDTO);

    //결재진행문서, 임시저장문서 개수 불러오기
    public int selectCountApDoc(int apDocStatus, String apDocWriter, Criteria cri);

    //결재대기문서 개수 불러오기...
    public int selectCountApCheck(String apDocWriter);

}
