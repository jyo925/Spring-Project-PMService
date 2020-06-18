package com.project.bit.dashBoard.service;

import com.project.bit.dashBoard.domain.*;

import java.util.*;

public interface DashBoardUserService {
    public UserCountVO getDashBoardUserCount(String userId);

    public List<TaskStatusCountVO> getTaskStatusCount(String userId);

    public List<UserOutputVO> getMyOutputList(String userId);

    public List<IssueStatusCountVO> getIssueStatusCount(String userId);

    public List<UserTaskVO> getMyTaskList(String userId);

}
