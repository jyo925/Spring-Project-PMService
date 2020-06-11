package com.project.bit.dashBoard.mapper;


import com.project.bit.dashBoard.domain.IssueStatusCountVO;
import com.project.bit.dashBoard.domain.TaskStatusCountVO;
import com.project.bit.dashBoard.domain.UserCountVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.*;

@Mapper
public interface DashBoardUserMapper {

    /**
     * @param user_id
     * @return
     */
    public UserCountVO selectCountProject(String user_id);

    /**
     * @param user_id
     * @return
     */
    public List<TaskStatusCountVO> selectCountTaskStatus(String user_id);

    /**
     * @param user_id
     * @return
     */
//    public List<OutputVO> selectMyOutputList(String user_id);

    /**
     * @param user_id
     * @return
     */
    public List<IssueStatusCountVO> selectCountIssueStatus(String user_id);

    /**
     * @param user_id
     * @return
     */
//    public List<MyTaskVO> selectMyTaskList(String user_id);

}
