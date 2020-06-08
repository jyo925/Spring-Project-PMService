package com.project.bit.project.service;

import java.util.List;

import com.project.bit.project.domain.ProjectMemberDTO;

public interface ProjectMemberService {
	public List<ProjectMemberDTO> getProjectMember(String projectId);
}
