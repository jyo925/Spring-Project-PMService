package com.project.bit.project.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bit.project.domain.ProjectDTO;
import com.project.bit.project.domain.ProjectStatusDTO;
import com.project.bit.project.domain.ProjectTypeDTO;
import com.project.bit.project.domain.ProjectVO;
import com.project.bit.project.mapper.ProjectMapper;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectMapper projectMapper;
	
	@Override
	public List<ProjectVO> getProjectListAll() {
		return projectMapper.selectProjectListAll();
	}

	@Override
	public List<ProjectVO> getProjectListByType(String typeCode) {
		if(typeCode.equals("all")) return this.getProjectListAll();
		else return projectMapper.selectProjectListByType(typeCode);
	}

	@Override
	public List<ProjectVO> getProjectSearch(String typeCode, String projectName) {
		if(typeCode.equals("all") && projectName.equals("")) return projectMapper.selectProjectListAll();
		else if (typeCode.equals("all") && !projectName.equals("")) return projectMapper.selectProjectListByName(projectName);
		else if (!typeCode.equals("all") && projectName.equals("")) return projectMapper.selectProjectListByType(typeCode);
		else return projectMapper.selectProjectListByTypeAndName(typeCode, projectName);
	}
	
	@Override
	public List<ProjectTypeDTO> getProjectTypeAll() {
		return projectMapper.selectProjectTypeListAll();
	}

	@Override
	public List<ProjectStatusDTO> getProjectStatusListAll() {
		return projectMapper.selectProjectStatusListAll();
	}

	@Override
	public int putProject(ProjectDTO projectDTO) {
		if(projectDTO == null) return 0;
		else {
			projectMapper.updateProject(projectDTO);
			return 1;
		}
	}

	@Override
	public void postProject(ProjectDTO projectDTO) {
		Date start = projectDTO.getProjectStart();
		String subName = projectDTO.getProjectSubName();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String date = sdf.format(start);
		
		projectDTO.setProjectCode(date + "-" + subName);
		System.out.println(projectDTO.getProjectCode());
		
		projectMapper.insertProject(projectDTO);
	}

	@Override
	public void removeProject(String projectCode) {
		projectMapper.deleteProject(projectCode);
		
	}
}
