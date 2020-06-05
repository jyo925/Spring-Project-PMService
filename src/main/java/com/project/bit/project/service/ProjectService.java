package com.project.bit.project.service;

import java.util.List;

import com.project.bit.project.domain.ProjectDTO;
import com.project.bit.project.domain.ProjectInfoVO;
import com.project.bit.project.domain.ProjectIssueStatusVO;
import com.project.bit.project.domain.ProjectStatusDTO;
import com.project.bit.project.domain.ProjectTaskStatusVO;
import com.project.bit.project.domain.ProjectTypeDTO;
import com.project.bit.project.domain.ProjectVO;


public interface ProjectService {
	public List<ProjectVO> getProjectListAll();
	public List<ProjectVO> getProjectListByType(String typeCode);
	public List<ProjectVO> getProjectSearch(String typeCode, String projectName);
	
	public ProjectInfoVO getProjectInfo(String projectId);
	public List<ProjectTaskStatusVO> getProjectTaskStatusCount(String projectId);
	public List<ProjectIssueStatusVO> getProjectIssueStatusCount(String projectId);
	public ProjectDTO getProjectOne(String projectId);
	
	public List<ProjectTypeDTO> getProjectTypeAll();
	public List<ProjectStatusDTO> getProjectStatusListAll();
}
