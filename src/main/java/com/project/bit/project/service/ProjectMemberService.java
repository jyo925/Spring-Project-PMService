package com.project.bit.project.service;

import java.util.List;

import com.project.bit.foo.domain.Users;
import com.project.bit.project.domain.ProjectMemberDTO;
import com.project.bit.project.domain.ProjectMemberVO;
import com.project.bit.project.domain.ProjectTaskVO;

public interface ProjectMemberService {
	void postProjectMember(ProjectMemberDTO projectMemberDTO);
	void deleteProjectMember(String projectJoinCode);
	List<ProjectMemberVO> getProjectMember(String projectId);
	List<ProjectTaskVO> getProjectMemberGantt(String projectCode);
	List<Users> getUserNoMember(); 
}
