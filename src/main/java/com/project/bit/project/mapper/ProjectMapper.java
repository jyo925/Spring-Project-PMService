package com.project.bit.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.bit.project.domain.ProjectTypeDTO;
import com.project.bit.project.domain.ProjectVO;

@Mapper
public interface ProjectMapper {
	public List<ProjectVO> selectProjectListAll();
	public List<ProjectVO> selectProjectListByType(String typeCode);
	public List<ProjectVO> selectProjectListByName(String projectName);
	public List<ProjectVO> selectProjectListByTypeAndName(@Param("typeCode") String typeCode,
														@Param("projectName") String projectName);
	
	public List<ProjectTypeDTO> selectProjectTypeListAll();
}	
