package com.project.bit.project.service;

import java.util.List;

import com.project.bit.project.domain.ProjectOutputDTO;
import com.project.bit.project.domain.ProjectOutputTypeDTO;

public interface ProjectOutputService {
	List<ProjectOutputDTO> getProjectOutput();
	List<ProjectOutputTypeDTO> getOutputType();
	void postProjectOutput(ProjectOutputDTO projectOutputDTO);
	void removeProjectOutput(String outputId);
}
