package com.project.bit.projects.service;

import org.springframework.stereotype.Service;

import com.project.bit.projects.domain.ProjectMembers;
import com.project.bit.projects.mapper.ProjectMembersMapper;

@Service
public class ProjectMembersServiceImpl implements ProjectMembersService {

	private final ProjectMembersMapper projectMembersMapper;
	
	public ProjectMembersServiceImpl(ProjectMembersMapper projectMembersMapper) {
		this.projectMembersMapper = projectMembersMapper;
	}

	@Override
	public void insertProjectMembers(ProjectMembers projectMembers) {
		projectMembersMapper.insertProjectMembers(projectMembers);

	}

}
