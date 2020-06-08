package com.project.bit.foo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bit.foo.domain.Teams;
import com.project.bit.foo.mapper.TeamsMapper;
import com.project.bit.foo.mapper.UserMapper;
import com.project.bit.project.domain.TeamDTO;

@Service
public class TeamsServiceImpl implements TeamsService {
	
	@Autowired
	private TeamsMapper teamsMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	public TeamsServiceImpl() {
		
	}

	@Override
	public void insertTeams(Teams teams) {
		teamsMapper.insertTeams(teams);

	}

	@Override
	public List<TeamDTO> getTeamAll() {
		// TODO Auto-generated method stub
		return userMapper.selectTeamAll();
	}

}
