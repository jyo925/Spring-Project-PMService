package com.project.bit.project.service;

import java.util.*;

import com.project.bit.project.domain.IssueStatusCountVO;
import com.project.bit.project.domain.ProjectStatusVO;

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
