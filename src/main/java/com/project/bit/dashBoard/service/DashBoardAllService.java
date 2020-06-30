package com.project.bit.dashBoard.service;

import java.util.List;

import com.project.bit.dashBoard.domain.IssueStatusCountVO;
import com.project.bit.dashBoard.domain.MonthlyProjectCountVO;
import com.project.bit.dashBoard.domain.ProjectStatusCountVO;
import com.project.bit.dashBoard.domain.ProjectStatusVO;
import com.project.bit.dashBoard.domain.ProjectTypeCountVO;
import com.project.bit.project.domain.ProjectDTO;

public interface DashBoardAllService {
	public List<ProjectStatusVO> getProjectAllStatusCount();
	public List<IssueStatusCountVO> getIssueAllStatus();
	public List<ProjectStatusCountVO> getProjectAllStatus();
	public List<ProjectTypeCountVO> getProjectAllType();
	public List<MonthlyProjectCountVO> getMonthlyProject();

	public List<ProjectDTO> getKeyProject();

}