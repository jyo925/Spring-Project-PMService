package com.project.bit.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.bit.foo.domain.Users;
import com.project.bit.project.domain.ProjectMemberDTO;
import com.project.bit.project.domain.ProjectMemberVO;
import com.project.bit.project.domain.ProjectTaskVO;

@Mapper
public interface ProjectMemberMapper {
	void insertProjectMember(ProjectMemberDTO projectMemberDTO);
	void deleteProjectMember(String projectJoinId);
	
	List<ProjectMemberVO> selectProjectMember(String projectId);
	List<ProjectTaskVO> selectProjectMemberGantt(String projectCode);
	List<Users> selectUserNoMember();
	
	/*
	 * int selectProjectMemberById(String userId); int
	 * selectProjectMemberAccount(@Param("projectCode") String projectCode
	 * ,@Param("dutyCode") String dutyCode);
	 */
	
}
