package com.project.bit.dashBoard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class UserCountVO {
    private int userProjectCount;
    private int userTaskCount;
    private int userIssueCount;
    private int userOutputCount;
}