package com.project.bit.dashBoard.mapper;


import com.project.bit.dashBoard.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.*;

@Mapper
public interface DashBoardUserMapper {

    public int selectTaskCount(String userId);
    public int selectProjectCount(String userId);
    public int selectIssueCount(String userId);
    public int selectOutputCount(String userId);


    public List<TaskStatusCountVO> selectTaskStatus(String userId);

    public List<UserOutputVO> selectMyOutputList(String userId);

    public List<IssueStatusCountVO> selectIssueStatus(String userId);

    public List<UserTaskVO> selectMyTaskList(String userId);

}
