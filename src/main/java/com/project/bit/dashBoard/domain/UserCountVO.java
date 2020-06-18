package com.project.bit.dashBoard.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCountVO {
    private int userProjectCount;
    private int userTaskCount;
    private int userIssueCount;
    private int userOutputCount;
}