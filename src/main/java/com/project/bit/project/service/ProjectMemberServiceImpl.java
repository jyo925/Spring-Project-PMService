package com.project.bit.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bit.project.domain.ProjectMemberDTO;
import com.project.bit.project.domain.ProjectMemberVO;
import com.project.bit.project.domain.ProjectTaskVO;
import com.project.bit.project.mapper.ProjectMemberMapper;

@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {
	
	@Autowired
	ProjectMemberMapper projectMemberMapper;

	@Override
	public void postProjectMember(ProjectMemberDTO projectMemberDTO) {
		projectMemberMapper.insertProjectMember(projectMemberDTO);
	}

	@Override
	public List<ProjectMemberVO> getProjectMember(String projectId) {
		return projectMemberMapper.selectProjectMember(projectId);
	}

	@Override
	public void deleteProjectMember(String projectJoinCode) {
		projectMemberMapper.deleteProjectMember(projectJoinCode);		
	}

	@Override
	public List<ProjectTaskVO> getProjectMemberGantt(String projectCode) {
		// TODO Auto-generated method stub
		return projectMemberMapper.selectProjectMemberGantt(projectCode);
	}

}
