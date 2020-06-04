package com.project.bit.project.mapper;

import java.util.*;

import com.project.bit.project.domain.IssueStatusCountVO;
import com.project.bit.project.domain.ProjectStatusVO;

public interface DashBoardAllMapper {
	 /**
     * @return
     */
    public List<ProjectStatusVO> selectProjectAllStatusCount();

    /**
     * @return
     */
    public List<IssueStatusCountVO> selectIssueAllStatusCount();
}
