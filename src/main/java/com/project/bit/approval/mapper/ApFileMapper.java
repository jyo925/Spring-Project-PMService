package com.project.bit.approval.mapper;

import com.project.bit.approval.domain.ApFileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ApFileMapper {

    //첨부파일 등록하기
    public int insertApFile(ApFileDTO apFileDTO);

    //첨부파일 조회
    public List<ApFileDTO> selectApFiles(String apDocNo);


}
