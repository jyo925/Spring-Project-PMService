package com.project.bit.foo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.project.bit.foo.domain.Duty;

@Mapper
public interface DutyMapper {
	
	@Select("SELECT * FROM DUTYS")
	List<Duty> getDutys();
	
}


