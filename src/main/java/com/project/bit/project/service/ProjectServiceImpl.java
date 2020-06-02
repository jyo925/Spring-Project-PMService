package com.project.bit.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bit.project.domain.ProjectVO;
import com.project.bit.project.mapper.ProjectMapper;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectMapper projectMapper;
	
	@Override
	public List<ProjectVO> getProjectListAll() {
		return projectMapper.selectProjectListAll();
	}

}
