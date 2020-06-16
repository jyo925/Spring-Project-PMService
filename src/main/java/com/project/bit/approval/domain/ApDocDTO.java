package com.project.bit.approval.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
