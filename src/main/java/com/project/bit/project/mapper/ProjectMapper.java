package com.project.bit.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.bit.project.domain.ProjectVO;

@Mapper
public interface ProjectMapper {
	public List<ProjectVO> selectProjectListAll();
}	
