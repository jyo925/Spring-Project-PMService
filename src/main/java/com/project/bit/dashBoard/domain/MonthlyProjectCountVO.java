package com.project.bit.dashBoard.domain;

import lombok.Data;

import java.util.Date;

@Data
public class MonthlyProjectCountVO {
    private int projectCount;
    private String projectType;
    private String projectMonth;
}
