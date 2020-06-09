package com.project.bit.dashBoard.mapper;

import java.util.*;

import com.project.bit.dashBoard.domain.IssueStatusCountVO;
import com.project.bit.dashBoard.domain.ProjectStatusVO;
import com.project.bit.project.domain.ProjectDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DashBoardAllMapper {
	 /**
     * @return
     */
    public List<ProjectStatusVO> selectProjectAllStatusCount();

    /**
     * @return
     */
    public List<IssueStatusCountVO> selectIssueAllStatusCount();

    public List<ProjectDTO> selectKeyProject();
}
