package com.project.bit.project.service;

import java.util.List;

import com.project.bit.project.domain.ProjectCriteria;
import com.project.bit.project.domain.ProjectDTO;
import com.project.bit.project.domain.ProjectStatusDTO;
import com.project.bit.project.domain.ProjectTypeDTO;
import com.project.bit.project.domain.ProjectVO;


public interface ProjectService {
	public List<ProjectVO> getProjectListAll(ProjectCriteria cri);
	public List<ProjectVO> getProjectListByType(ProjectCriteria cri, String typeCode);
	public List<ProjectVO> getProjectSearch(ProjectCriteria cri, String typeCode, String projectName);
	
	public int putProject(ProjectDTO projectDTO);
	public void postProject(ProjectDTO projectDTO);
	public void removeProject(String projectCode);
	
	public List<ProjectTypeDTO> getProjectTypeAll();
	public List<ProjectStatusDTO> getProjectStatusListAll();
	
	public int getProjectListAllAccount();
	int getProjectListAccount(String typeCode);

	/*
	 * int selectProjectListAccountByName(String projectName); int
	 * selectProjectListAccountByNameAndType(String typeCode, String projectName);
	 */
	int getPageTotal(String typeCode, String name);
	int checkProjectSubName(String subName);
}
