package com.project.bit.dashBoard.service;

import java.util.*;

import com.project.bit.dashBoard.domain.IssueStatusCountVO;
import com.project.bit.dashBoard.domain.ProjectStatusVO;

public interface DashBoardAllService {

	/**
	 * @return
	 */
	public List<ProjectStatusVO> getProjectAllStatusCount();

	/**
	 * @return
	 */
	public List<IssueStatusCountVO> getIssueAllStatusCount();


}
