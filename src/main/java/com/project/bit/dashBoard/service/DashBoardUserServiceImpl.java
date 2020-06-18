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
    public UserCountVO getDashBoardUserCount(String userId) {
        /* or hashmap 사용 */
        UserCountVO countVO = new UserCountVO();
        countVO.setUserIssueCount(dashBoardUserMapper.selectIssueCount(userId).getUserIssueCount());
        countVO.setUserOutputCount(dashBoardUserMapper.selectOutputCount(userId).getUserOutputCount());
        countVO.setUserProjectCount(dashBoardUserMapper.selectProjectCount(userId).getUserProjectCount());
        countVO.setUserTaskCount(dashBoardUserMapper.selectTaskCount(userId).getUserTaskCount());
        return countVO;
    }

    @Override
    public List<TaskStatusCountVO> getTaskStatusCount(String userId) {
        return dashBoardUserMapper.selectTaskStatus(userId);
    }

    @Override
    public List<IssueTypeCountVO> getIssueStatusCount(String userId) {
        return dashBoardUserMapper.selectIssueStatus(userId);
    }
}
