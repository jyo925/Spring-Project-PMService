package com.project.bit.dashBoard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TaskStatusCountVO {
    private String taskStatusName;
    private int taskStatusAll;
    private int taskStatusProcess;
    private int taskStatusStandBy;
    private int taskStatusComplete;
}