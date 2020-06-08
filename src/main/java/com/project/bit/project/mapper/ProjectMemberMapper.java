package com.project.bit.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.bit.project.domain.ProjectMemberDTO;

@Mapper
public interface ProjectMemberMapper {
	public List<ProjectMemberDTO> selectProjectMember(String projectId);
}
