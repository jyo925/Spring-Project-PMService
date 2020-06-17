package com.project.bit.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.bit.project.domain.ProjectOutputDTO;
import com.project.bit.project.domain.ProjectOutputTypeDTO;

@Mapper
public interface ProjectOutputMapper {
	List<ProjectOutputDTO> selectProjectOutput();
	List<ProjectOutputTypeDTO> selectProjectOutputType();
	void insertProjectOutput(ProjectOutputDTO projectOutputDTO);
	void deleteProjectOutput(String outputId);
}
