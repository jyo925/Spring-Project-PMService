package com.project.bit.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.bit.project.domain.ProjectMemberDTO;
import com.project.bit.project.domain.ProjectMemberVO;

@Mapper
public interface ProjectMemberMapper {
	public void insertProjectMember(ProjectMemberDTO projectMemberDTO);
	public void deleteProjectMember(String projectJoinCode);
	public List<ProjectMemberVO> selectProjectMember(String projectId);
}
