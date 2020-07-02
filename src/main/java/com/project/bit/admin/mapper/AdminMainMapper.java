package com.project.bit.admin.mapper;

import com.project.bit.admin.domain.OutputStatusCountVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMainMapper {

    public List<OutputStatusCountVO> selectOutputAllStatus();


}
