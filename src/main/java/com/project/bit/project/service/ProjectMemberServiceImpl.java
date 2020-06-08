package com.project.bit.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bit.project.domain.ProjectMemberDTO;
import com.project.bit.project.mapper.ProjectMemberMapper;

@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {
	
	@Autowired
	ProjectMemberMapper projectMemberMapper;

	@Override
	public List<ProjectMemberDTO> getProjectMember(String projectId) {
		return projectMemberMapper.selectProjectMember(projectId);
	}

}
