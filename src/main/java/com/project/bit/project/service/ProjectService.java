package com.project.bit.project.service;

import java.util.List;

import com.project.bit.project.domain.ProjectDTO;
import com.project.bit.project.domain.ProjectStatusDTO;
import com.project.bit.project.domain.ProjectTypeDTO;
import com.project.bit.project.domain.ProjectVO;


public interface ProjectService {
	public List<ProjectVO> getProjectListAll();
	public List<ProjectVO> getProjectListByType(String typeCode);
	public List<ProjectVO> getProjectSearch(String typeCode, String projectName);
	
	public int putProject(ProjectDTO projectDTO);
	public void postProject(ProjectDTO projectDTO);
	public void removeProject(String projectCode);
	
	public List<ProjectTypeDTO> getProjectTypeAll();
	public List<ProjectStatusDTO> getProjectStatusListAll();
}
