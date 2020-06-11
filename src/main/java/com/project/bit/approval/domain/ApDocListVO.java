package com.project.bit.approval.domain;


import lombok.Data;

import java.util.Date;

@Data
public class ApDocListVO {

    private Long apDocNo;
    private String apFormName; //추가
    private String apDocTitle;
    private String apDocWriter; //사용자 아이디
    private String userName; //사용자 이름
    private String projectCode; //사용자 속한 프로젝트
    private Date apDocCreateDate;
    private Date apDocEndDate;
    private int apDocStatus;

}
