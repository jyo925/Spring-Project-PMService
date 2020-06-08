package com.project.bit.foo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bit.foo.domain.Positions;
import com.project.bit.foo.mapper.PositionMapper;
import com.project.bit.foo.mapper.UserMapper;
import com.project.bit.project.domain.PositionDTO;

@Service
public class PositionsServiceImpl implements PositionsService {

	private final PositionMapper positionsMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	public PositionsServiceImpl(PositionMapper positionsMapper) {
		this.positionsMapper = positionsMapper;
	}

	@Override
	public void insertPosition(Positions positions) {
		positionsMapper.insertPosition(positions);

	}

	@Override
	public List<PositionDTO> getPositionAll() {
		// TODO Auto-generated method stub
		return userMapper.selectPositionAll();
	}

}
