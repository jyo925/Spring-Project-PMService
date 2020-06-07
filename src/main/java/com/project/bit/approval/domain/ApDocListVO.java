package com.project.bit.approval.domain;


import lombok.Data;

import java.util.Date;

@Data
public class ApDocListVO {

    private Long apDocNo;
    private String apFormName; //추가
    private String apDocTitle;
    private String apDocWriter;
    private String apDocWriterName; //추가
    private String apDocWriterProjectCode; //추가
    private Date apDocCreateDate;
    private Date apDocEndDate;
    private int apDocStatus;

}
