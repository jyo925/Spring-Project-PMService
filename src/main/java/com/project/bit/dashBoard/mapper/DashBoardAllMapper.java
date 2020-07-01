package com.project.bit.dashBoard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.bit.dashBoard.domain.IssueStatusCountVO;
import com.project.bit.dashBoard.domain.MonthlyProjectCountVO;
import com.project.bit.dashBoard.domain.ProjectStatusCountVO;
import com.project.bit.dashBoard.domain.ProjectStatusVO;
import com.project.bit.dashBoard.domain.ProjectTypeCountVO;
import com.project.bit.project.domain.ProjectDTO;

@Mapper
public interface DashBoardAllMapper {
    public List<ProjectStatusVO> selectProjectAllStatusCount();
    public List<IssueStatusCountVO> selectIssueAllStatus();
    public List<ProjectStatusCountVO> selectProjectAllStatus();
    public List<ProjectTypeCountVO> selectProjectAllType();
    public List<MonthlyProjectCountVO> selectMonthlyProjectStatus();

    public List<ProjectDTO> selectKeyProject();
}
