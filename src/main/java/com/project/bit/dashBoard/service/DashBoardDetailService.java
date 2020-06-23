package com.project.bit.dashBoard.service;

import java.util.List;

import com.project.bit.project.domain.ProjectDTO;

public interface DashBoardDetailService {
	public List<ProjectDTO> findProjectList();
	public List<ProjectDTO> searchProjectList(String keyword);
}
