package com.project.bit.project.service;

import java.util.List;

import com.project.bit.project.domain.ProjectDTO;
import com.project.bit.project.domain.ProjectInfoVO;
import com.project.bit.project.domain.ProjectIssueStatusVO;
import com.project.bit.project.domain.ProjectTaskStatusVO;

public interface ProjectDetailService {
	public ProjectInfoVO getProjectInfo(String projectId);
	public List<ProjectTaskStatusVO> getProjectTaskStatusCount(String projectId);
	public List<ProjectIssueStatusVO> getProjectIssueStatusCount(String projectId);
	public ProjectDTO getProjectOne(String projectId);
}
