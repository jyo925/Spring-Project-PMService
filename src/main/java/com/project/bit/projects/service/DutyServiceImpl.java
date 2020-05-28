package com.project.bit.projects.service;

import org.springframework.stereotype.Service;

import com.project.bit.projects.mapper.DutyMapper;

@Service
public class DutyServiceImpl implements DutyService{

	private final DutyMapper dutyMapper;
	
	public DutyServiceImpl(DutyMapper positionMapper) {
		this.dutyMapper = positionMapper;
	}

	@Override
	public void insertDuty(String DUTY_CODE, String DUTY_NAME) {
		dutyMapper.insertDuty(DUTY_CODE, DUTY_NAME);
		
	}

}
