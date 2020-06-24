package com.project.bit.foo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.project.bit.project.domain.ProjectStatusDTO;

@Mapper
public interface ProjectStatusGetMapper {

	@Select("SELECT * FROM PROJECT_STATUS")
	List<ProjectStatusDTO> selectAllStatusNames();
	
}
