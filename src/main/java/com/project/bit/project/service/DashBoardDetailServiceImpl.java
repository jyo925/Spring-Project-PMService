package com.project.bit.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.project.bit.project.domain.ProjectDTO;
import com.project.bit.project.mapper.DashBoardDetailMapper;

@Service
public class DashBoardDetailServiceImpl implements DashBoardDetailService {

	@Autowired
	private DashBoardDetailMapper dashBoardDetailMapper;
	
	@Override
	public List<ProjectDTO> findProjectList() {
	
		return dashBoardDetailMapper.selectProjectList();
	}
	
	
	

}
