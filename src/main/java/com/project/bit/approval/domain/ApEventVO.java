package com.project.bit.approval.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class ApEventVO {

    private String userName;
    private String userId;
    private String apFormNo;
    private Date apStartDate;
    private Date apEndDate;
}
