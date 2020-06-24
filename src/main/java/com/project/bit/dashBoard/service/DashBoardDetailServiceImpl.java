package com.project.bit.dashBoard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bit.project.domain.ProjectDTO;
import com.project.bit.dashBoard.mapper.DashBoardDetailMapper;

@Service
public class DashBoardDetailServiceImpl implements DashBoardDetailService {

	@Autowired
	private DashBoardDetailMapper dashBoardDetailMapper;
	
	@Override
	public List<ProjectDTO> findProjectList() {
	
		return dashBoardDetailMapper.selectProjectList();
	}

	@Override
	public List<ProjectDTO> searchProjectList(String keyword) {
		return dashBoardDetailMapper.searchProjectList(keyword);
	}
	

}
