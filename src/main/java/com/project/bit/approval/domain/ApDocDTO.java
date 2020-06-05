package com.project.bit.approval.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ApDocDTO {

    private Long apDocNo;
    private int apFormNo;
    private String apDocWriter;
    private String apDocTitle;
    private String apDocContent;
    private int apDocStatus;
    private int apDocStep;
    private Date apDocCreateDate;
    private Date apDocEndDate;
}
