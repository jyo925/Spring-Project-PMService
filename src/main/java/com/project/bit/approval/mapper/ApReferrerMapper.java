package com.project.bit.approval.mapper;

import com.project.bit.approval.domain.ReferrerVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ApReferrerMapper {

    public List<ReferrerVO> selectReferrerUserList();

    public void insertApDocReferrer(long apDocNo, String apReferrer);
    
    //참조자 목록 불러오기
    public List<ReferrerVO> selectApDocReferrers(String apDocNo);
}
