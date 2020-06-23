package com.project.bit.dashBoard.domain;

import lombok.Data;

import java.util.Date;

@Data
public class UserTaskVO {
    private String projectName;
    private String taskName;
    private String taskStatusCode;
    private String taskStatus;
    private Date taskStart;
    private Date taskFinish;
}
