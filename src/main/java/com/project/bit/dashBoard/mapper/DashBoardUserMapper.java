package com.project.bit.dashBoard.mapper;


import com.project.bit.dashBoard.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.*;

@Mapper
public interface DashBoardUserMapper {

    public UserCountVO selectTaskCount(String userId);
    public UserCountVO selectProjectCount(String userId);
    public UserCountVO selectIssueCount(String userId);
    public UserCountVO selectOutputCount(String userId);


    public List<TaskStatusCountVO> selectTaskStatus(String userId);

    public List<UserOutputVO> selectMyOutputList(String userId);

    public List<IssueStatusCountVO> selectIssueStatus(String userId);

    public List<UserTaskVO> selectMyTaskList(String userId);

}
