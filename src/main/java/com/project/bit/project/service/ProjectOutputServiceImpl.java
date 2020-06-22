package com.project.bit.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bit.project.domain.ProjectOutputDTO;
import com.project.bit.project.domain.ProjectOutputTypeDTO;
import com.project.bit.project.mapper.ProjectOutputMapper;

@Service
public class ProjectOutputServiceImpl implements ProjectOutputService {

	@Autowired
	private ProjectOutputMapper projectOutputMapper;
	
	@Override
	public List<ProjectOutputTypeDTO> getOutputType() {
		// TODO Auto-generated method stub
		return projectOutputMapper.selectProjectOutputType();
	}

	@Override
	public List<ProjectOutputDTO> getProjectOutput() {
		// TODO Auto-generated method stub
		return projectOutputMapper.selectProjectOutput();
	}

	@Override
	public void postProjectOutput(ProjectOutputDTO projectOutputDTO) {
		projectOutputMapper.insertProjectOutput(projectOutputDTO);
		
	}

	@Override
	public void removeProjectOutput(String outputId) {
		projectOutputMapper.deleteProjectOutput(outputId);
		
	}

}
