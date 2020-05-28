package com.project.bit.projects.service;

import org.springframework.stereotype.Service;

import com.project.bit.projects.domain.Positions;
import com.project.bit.projects.mapper.PositionMapper;

@Service
public class PositionsServiceImpl implements PositionsService {

	private final PositionMapper positionsMapper;
	
	public PositionsServiceImpl(PositionMapper positionsMapper) {
		this.positionsMapper = positionsMapper;
	}

	@Override
	public void insertPosition(Positions positions) {
		positionsMapper.insertPosition(positions);

	}

}
