package com.project.bit.approval.mapper;

import com.project.bit.approval.domain.ApFileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ApFileMapper {


    public int insertApFile(ApFileDTO apFileDTO);

    public List<ApFileDTO> selectApFiles(String apDocNo);


}
