package com.project.bit.project.service;

import java.util.List;

import com.project.bit.project.domain.ProjectMemberDTO;
import com.project.bit.project.domain.ProjectMemberVO;

public interface ProjectMemberService {
	public void postProjectMember(ProjectMemberDTO projectMemberDTO);
	public void deleteProjectMember(String projectJoinCode);
	public List<ProjectMemberVO> getProjectMember(String projectId);
}
