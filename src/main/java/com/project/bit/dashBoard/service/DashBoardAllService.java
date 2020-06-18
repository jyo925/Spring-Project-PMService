package com.project.bit.dashBoard.service;

import java.util.*;

import com.project.bit.dashBoard.domain.IssueStatusCountVO;
import com.project.bit.dashBoard.domain.ProjectStatusVO;
import com.project.bit.project.domain.ProjectDTO;

public interface DashBoardAllService {

	/**
	 * @return
	 */
	public List<ProjectStatusVO> getProjectAllStatusCount();

	/**
	 * @return
	 */
	public List<IssueStatusCountVO> getIssueAllStatusCount();

	public List<ProjectDTO> getKeyProject();

}
