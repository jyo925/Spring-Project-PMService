package com.project.bit.dashBoard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@ToString
public class UserCountVO {
    private int userProjectCount;
    private int userTaskCount;
    private int userIssueCount;
    private int userOutputCount;
}