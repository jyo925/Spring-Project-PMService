package com.project.bit.foo.service;

import java.util.List;

import com.project.bit.foo.domain.Teams;
import com.project.bit.project.domain.TeamDTO;

public interface TeamsService {
	
	void insertTeams(Teams teams);
	
	List<TeamDTO> getTeamAll();

}
