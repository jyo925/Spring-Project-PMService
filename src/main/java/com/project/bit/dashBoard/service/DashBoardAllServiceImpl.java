package com.project.bit.dashBoard.service;

import com.project.bit.dashBoard.domain.IssueStatusCountVO;
import com.project.bit.dashBoard.domain.ProjectStatusVO;
import com.project.bit.dashBoard.mapper.DashBoardAllMapper;
import com.project.bit.project.domain.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashBoardAllServiceImpl implements DashBoardAllService {

    @Autowired
    private DashBoardAllMapper dashBoardAllMapper;

    @Override
    public List<ProjectStatusVO> getProjectAllStatusCount() {
        return dashBoardAllMapper.selectProjectAllStatusCount();
    }

    @Override
    public List<IssueStatusCountVO> getIssueAllStatusCount() {
        return null;
    }

    @Override
    public List<ProjectDTO> getKeyProject() {
        return dashBoardAllMapper.selectKeyProject();
    }
}
