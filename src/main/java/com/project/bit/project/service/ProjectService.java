package com.project.bit.project.service;

import java.util.List;

import com.project.bit.project.domain.ProjectTypeDTO;
import com.project.bit.project.domain.ProjectVO;


public interface ProjectService {
	public List<ProjectVO> getProjectListAll();
	public List<ProjectVO> getProjectListByType(String typeCode);
	public List<ProjectVO> getProjectSearch(String typeCode, String projectName);
	
	public List<ProjectTypeDTO> getProjectTypeAll();
}
