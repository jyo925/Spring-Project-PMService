package com.project.bit.project.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.bit.project.domain.ProjectOutputDTO;
import com.project.bit.project.domain.ProjectOutputTypeDTO;

public interface ProjectOutputService {
	List<ProjectOutputDTO> getProjectDetailOutput(String projectCode);
	List<ProjectOutputDTO> getProjectOutput();
	List<ProjectOutputTypeDTO> getOutputType();
	void postProjectOutput(ProjectOutputDTO projectOutputDTO);
	void postProjectOutput(MultipartFile outputFile);
	void removeProjectOutput(String outputId);
	void putProjectOutput(ProjectOutputDTO projectOutputDTO);
}
