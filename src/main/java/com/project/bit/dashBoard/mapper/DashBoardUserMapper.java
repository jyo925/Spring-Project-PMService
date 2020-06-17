package com.project.bit.dashBoard.mapper;


import com.project.bit.dashBoard.domain.IssueTypeCountVO;
import com.project.bit.dashBoard.domain.TaskStatusCountVO;
import com.project.bit.dashBoard.domain.UserCountVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.*;

@Mapper
public interface DashBoardUserMapper {

    public UserCountVO selectTaskCount(String userId);
    public UserCountVO selectProjectCount(String userId);
    public UserCountVO selectIssueCount(String userId);
    public UserCountVO selectOutputCount(String userId);


    public List<TaskStatusCountVO> selectMyTaskList(String userId);

//    public List<OutputVO> selectMyOutputList(String userId);

    public List<IssueTypeCountVO> selectCountIssueStatus(String userId);

//    public List<MyTaskVO> selectMyTaskList(String userId);

}
