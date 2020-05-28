package com.project.bit.projects.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bit.projects.domain.Teams;
import com.project.bit.projects.mapper.TeamsMapper;

@Service
public class TeamsServiceImpl implements TeamsService {
	
	@Autowired
	private TeamsMapper teamsMapper;
	
	public TeamsServiceImpl() {
		
	}

	@Override
	public void insertTeams(Teams teams) {
		teamsMapper.insertTeams(teams);

	}

}
