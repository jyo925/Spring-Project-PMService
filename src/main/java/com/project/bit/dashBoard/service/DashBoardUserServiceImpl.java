package com.project.bit.dashBoard.service;

import com.project.bit.dashBoard.domain.*;
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
        countVO.setUserIssueCount(dashBoardUserMapper.selectIssueCount(userId));
        countVO.setUserOutputCount(dashBoardUserMapper.selectOutputCount(userId));
        countVO.setUserProjectCount(dashBoardUserMapper.selectProjectCount(userId));
        countVO.setUserTaskCount(dashBoardUserMapper.selectTaskCount(userId));
        System.err.println(countVO);
        return countVO;
    }

    @Override
    public List<TaskStatusCountVO> getTaskStatusCount(String userId) {
        return dashBoardUserMapper.selectTaskStatus(userId);
    }

    @Override
    public List<UserOutputVO> getMyOutputList(String userId) {
        return dashBoardUserMapper.selectMyOutputList(userId);
    }

    @Override
    public List<IssueStatusCountVO> getIssueStatusCount(String userId) {
        return dashBoardUserMapper.selectIssueStatus(userId);
    }

    @Override
    public List<UserTaskVO> getMyTaskList(String userId) {
        return dashBoardUserMapper.selectMyTaskList(userId);
    }
}
