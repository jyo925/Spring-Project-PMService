package com.project.bit.dashBoard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.bit.project.domain.ProjectDTO;

@Mapper
public interface DashBoardDetailMapper {
	public ProjectDTO selectProjectByName(String projectName);
	public List<ProjectDTO> selectProjectList(); 
}
