package com.project.bit.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.bit.project.domain.ProjectDTO;
import com.project.bit.project.domain.ProjectInfoVO;
import com.project.bit.project.domain.ProjectIssueStatusVO;
import com.project.bit.project.domain.ProjectStatusDTO;
import com.project.bit.project.domain.ProjectTaskStatusVO;
import com.project.bit.project.domain.ProjectTypeDTO;
import com.project.bit.project.domain.ProjectVO;

@Mapper
public interface ProjectMapper {
	public List<ProjectVO> selectProjectListAll();
	public List<ProjectVO> selectProjectListByType(String typeCode);
	public List<ProjectVO> selectProjectListByName(String projectName);
	public List<ProjectVO> selectProjectListByTypeAndName(@Param("typeCode") String typeCode,
														@Param("projectName") String projectName);
	
	public ProjectInfoVO selectProjectInfo(String projectId);
	public List<ProjectTaskStatusVO> selectProjectTaskStatusCount(String projectId);
	public List<ProjectIssueStatusVO> selectProjectIssueStatusCount(String projectId);
	public ProjectDTO selectProjectOne(String projectId);
	
	public List<ProjectTypeDTO> selectProjectTypeListAll();
	public List<ProjectStatusDTO> selectProjectStatusListAll();
}	
