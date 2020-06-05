package com.project.bit.foo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.project.bit.foo.domain.Teams;

@Mapper
public interface TeamsMapper {
	
	@Insert("insert into teams(TEAM_CODE, TEAM_NAME, TEAM_UPPER, TEAM_SEQ, TEAM_USE) "
			+ "values(#{TEAM_CODE}, #{TEAM_NAME}, #{TEAM_UPPER}, #{TEAM_SEQ}, #{TEAM_USE})")
	void insertTeams(Teams teams);

}
