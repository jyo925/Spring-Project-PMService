package com.project.bit.project.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bit.project.domain.ProjectCriteria;
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
	public List<ProjectVO> getProjectListAll(ProjectCriteria cri) {
		return projectMapper.selectProjectListAll(cri);
	}

	@Override
	public List<ProjectVO> getProjectListByType(ProjectCriteria cri, String typeCode) {
		if(typeCode.equals("all")) return projectMapper.selectProjectListAll(cri);
		else return projectMapper.selectProjectListByType(cri, typeCode);
	}

	@Override
	public List<ProjectVO> getProjectSearch(ProjectCriteria cri, String typeCode, String projectName) {
		if(typeCode.equals("all") && projectName.equals("")) return projectMapper.selectProjectListAll(cri);
		else if (typeCode.equals("all") && !projectName.equals("")) return projectMapper.selectProjectListByName(cri, projectName);
		else if (!typeCode.equals("all") && projectName.equals("")) return projectMapper.selectProjectListByType(cri, typeCode);
		else return projectMapper.selectProjectListByTypeAndName(cri, typeCode, projectName);
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
		
		if(projectDTO.getProjectPm() != "" ) {
			projectMapper.insertProjectPm(projectDTO);		
		}
		
		if(projectDTO.getProjectPmo() != "") {
			projectMapper.insertProjectPmo(projectDTO);
		}
		
	}

	@Override
	public void removeProject(String projectCode) {
		projectMapper.deleteProject(projectCode);
		
	}

	@Override
	public int getProjectListAllAccount() {
		return projectMapper.getProjectListAllAccount();
	}

	@Override
	public int getProjectListAccount(String typeCode) {
		// TODO Auto-generated method stub
		return projectMapper.selectProjectListAccount(typeCode);
	}

	@Override
	public int getPageTotal(String typeCode, String name) {
		if(!typeCode.equals("all") && name.equals("")) return projectMapper.selectProjectListAccount(typeCode);
		else if(typeCode.equals("all") && !name.equals("")) return projectMapper.selectProjectListAccountByName(name);
		else return projectMapper.selectProjectListAccountByNameAndType(typeCode, name);
		
	}

	@Override
	public int checkProjectSubName(String subName) {
		// TODO Auto-generated method stub
		return projectMapper.checkProjectSubName(subName);
	}
}
