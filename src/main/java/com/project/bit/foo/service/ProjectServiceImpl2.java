package com.project.bit.foo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.bit.foo.domain.Project;
import com.project.bit.foo.mapper.ProjectMapper2;

@Service
public class ProjectServiceImpl2 implements ProjectService2 {
	
	private final ProjectMapper2 projectMapper;
	
	public ProjectServiceImpl2(ProjectMapper2 projectMapper) {
		this.projectMapper = projectMapper;
	}

	@Override
	public List<Project> selectAllProjects() {
		return projectMapper.selectAllProjects();
	}

}
