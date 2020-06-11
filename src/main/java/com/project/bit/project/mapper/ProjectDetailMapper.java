package com.project.bit.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.bit.project.domain.ProjectDTO;
import com.project.bit.project.domain.ProjectInfoVO;
import com.project.bit.project.domain.ProjectIssueStatusVO;
import com.project.bit.project.domain.ProjectTaskStatusVO;

@Mapper
public interface ProjectDetailMapper {
	public ProjectInfoVO selectProjectInfo(String projectId);
	public List<ProjectTaskStatusVO> selectProjectTaskStatusCount(String projectId);
	public List<ProjectIssueStatusVO> selectProjectIssueStatusCount(String projectId);
	public ProjectDTO selectProjectOne(String projectId);
}
