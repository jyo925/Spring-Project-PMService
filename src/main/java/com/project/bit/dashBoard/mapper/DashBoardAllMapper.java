package com.project.bit.dashBoard.mapper;

import java.util.*;

import com.project.bit.dashBoard.domain.IssueStatusCountVO;
import com.project.bit.dashBoard.domain.MonthlyProjectCountVO;
import com.project.bit.dashBoard.domain.ProjectStatusCountVO;
import com.project.bit.dashBoard.domain.ProjectStatusVO;
import com.project.bit.project.domain.ProjectDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DashBoardAllMapper {
    public List<ProjectStatusVO> selectProjectAllStatusCount();
    public List<IssueStatusCountVO> selectIssueAllStatus();
    public List<ProjectStatusCountVO> selectProjectAllStatus();
    public List<MonthlyProjectCountVO> selectMonthlyProjectStatus();

    public List<ProjectDTO> selectKeyProject();
}
