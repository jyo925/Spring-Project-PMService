package com.project.bit.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bit.project.domain.ProjectDTO;
import com.project.bit.project.domain.ProjectInfoVO;
import com.project.bit.project.domain.ProjectIssueStatusVO;
import com.project.bit.project.domain.ProjectTaskStatusVO;
import com.project.bit.project.mapper.ProjectDetailMapper;

@Service
public class ProjectDetailServiceImpl implements ProjectDetailService {
	
	@Autowired
	private ProjectDetailMapper projectDetailMapper;

	@Override
	public ProjectInfoVO getProjectInfo(String projectId) {
		return projectDetailMapper.selectProjectInfo(projectId);
	}

	@Override
	public List<ProjectTaskStatusVO> getProjectTaskStatusCount(String projectId) {
		return projectDetailMapper.selectProjectTaskStatusCount(projectId);
	}

	@Override
	public List<ProjectIssueStatusVO> getProjectIssueStatusCount(String projectId) {
		return projectDetailMapper.selectProjectIssueStatusCount(projectId);
	}

	@Override
	public ProjectDTO getProjectOne(String projectId) {
		return projectDetailMapper.selectProjectOne(projectId);
	}
}
