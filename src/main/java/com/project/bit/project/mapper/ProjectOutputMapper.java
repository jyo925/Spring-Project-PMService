package com.project.bit.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.bit.project.domain.ProjectOutputDTO;
import com.project.bit.project.domain.ProjectOutputTypeDTO;

@Mapper
public interface ProjectOutputMapper {
	List<ProjectOutputDTO> selectProjectDetailOutput(String projectCode);
	List<ProjectOutputDTO> selectProjectOutput();
	List<ProjectOutputTypeDTO> selectProjectOutputType();
	List<ProjectOutputDTO> selectProjectOutputByType(@Param("projectCode") String projectCode, @Param("typeCode") String typeCode);
	void insertProjectOutput(ProjectOutputDTO projectOutputDTO);
	void deleteProjectOutput(String outputId);
	void updateProjectOutput(ProjectOutputDTO projectOutputDTO);
}
