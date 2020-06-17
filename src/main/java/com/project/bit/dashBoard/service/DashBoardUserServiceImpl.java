package com.project.bit.dashBoard.service;

import com.project.bit.dashBoard.domain.IssueTypeCountVO;
import com.project.bit.dashBoard.domain.TaskStatusCountVO;
import com.project.bit.dashBoard.domain.UserCountVO;
import com.project.bit.dashBoard.mapper.DashBoardUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashBoardUserServiceImpl implements DashBoardUserService {

    @Autowired
    private DashBoardUserMapper dashBoardUserMapper;

    @Override
    public UserCountVO getDashBoardUserCount(String user_id) {
        return null;
    }

    @Override
    public List<TaskStatusCountVO> getTaskStatusCountUser(String user_id) {
        return null;
    }

    @Override
    public List<IssueTypeCountVO> getIssueStatusCountUser(String user_id) {
        return null;
    }
}
