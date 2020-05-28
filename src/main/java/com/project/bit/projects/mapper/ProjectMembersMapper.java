package com.project.bit.projects.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.project.bit.projects.domain.ProjectMembers;

@Mapper
public interface ProjectMembersMapper {
	
	@Insert("insert into ProjectMembers(PROJECT_JOINCODE, PROJECT_CODE, USER_ID, DUTY_CODE) "
			+ "values(#{PROJECT_JOINCODE}, #{PROJECT_CODE}, #{USER_ID}, #{DUTY_CODE})")
	void insertProjectMembers(ProjectMembers ProjectMembers);

}
