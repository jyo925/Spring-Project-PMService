package com.project.bit.approval.mapper;

import com.project.bit.approval.domain.ApFormDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ApDocMapper {

    public ApFormDTO selectApForm(String apFormNo);

}
