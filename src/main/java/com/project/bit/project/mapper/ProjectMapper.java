package com.project.bit.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.bit.project.domain.ProjectCriteria;
import com.project.bit.project.domain.ProjectDTO;
import com.project.bit.project.domain.ProjectStatusDTO;
import com.project.bit.project.domain.ProjectTypeDTO;
import com.project.bit.project.domain.ProjectVO;

@Mapper
public interface ProjectMapper {
	public List<ProjectVO> selectProjectListAll(ProjectCriteria cri);
	public List<ProjectVO> selectProjectListByType(@Param("cri") ProjectCriteria cri,@Param("typeCode") String typeCode);
	public List<ProjectVO> selectProjectListByName(@Param("cri") ProjectCriteria cri, @Param("projectName") String projectName);
	public List<ProjectVO> selectProjectListByTypeAndName(@Param("cri") ProjectCriteria cri, 
			@Param("typeCode") String typeCode, @Param("projectName") String projectName);
	
	public List<ProjectVO> selectProjectAll();
	
	public void updateProject(ProjectDTO projectDTO);
	public void insertProject(ProjectDTO projectDTO);
	public void deleteProject(String projectCode);
	public void insertProjectPm(ProjectDTO projectDTO);
	public void insertProjectPmo(ProjectDTO projectDTO);
	
	public List<ProjectTypeDTO> selectProjectTypeListAll();
	public List<ProjectStatusDTO> selectProjectStatusListAll();
	
	public int getProjectListAllAccount();
	int selectProjectListAccount(String typeCode);
	int selectProjectListAccountByName(String projectName);
	int selectProjectListAccountByNameAndType(@Param("typeCode") String typeCode, @Param("projectName") String projectName);
	int checkProjectSubName(String subName);
}	

