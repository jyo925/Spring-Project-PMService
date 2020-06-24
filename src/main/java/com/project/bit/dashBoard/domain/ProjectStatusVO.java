package com.project.bit.dashBoard.domain;

import lombok.Data;

@Data
public class ProjectStatusVO {
    private String projectStatusName;
    private int projectStatusAll;
    private int projectStatusProcess;
    private int projectStatusStandBy;
    private int projectStatusComplete;
    private int projectStatusStop;
}