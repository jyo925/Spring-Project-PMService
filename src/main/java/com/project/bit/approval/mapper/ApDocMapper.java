package com.project.bit.approval.mapper;

import com.project.bit.approval.domain.ApDocDTO;
import com.project.bit.approval.domain.ApDocListVO;
import com.project.bit.approval.domain.ApFormDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ApDocMapper {

    public ApFormDTO selectApForm(String apFormNo);

    public int insertApDoc(ApDocDTO apDocDTO);

    public List<ApDocListVO> selectApProgressList(String apDocWriter);

}
