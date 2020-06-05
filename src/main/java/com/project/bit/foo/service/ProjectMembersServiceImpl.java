package com.project.bit.foo.service;

import org.springframework.stereotype.Service;

import com.project.bit.foo.domain.ProjectMembers;
import com.project.bit.foo.mapper.ProjectMembersMapper;

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
