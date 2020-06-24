package com.project.bit.dashBoard.domain;

import lombok.Data;

import java.util.Date;

@Data
public class UserOutputVO {
    private String projectName;
    private String taskName;
    private String outputName;
    private Date outputDate;
}
