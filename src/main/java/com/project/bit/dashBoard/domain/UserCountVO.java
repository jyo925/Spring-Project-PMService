package com.project.bit.dashBoard.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserCountVO {
    private int userProjectCount;
    private int userTaskCount;
    private int userIssueCount;
    private int userOutputCount;
}