package com.project.bit.approval.domain;

import lombok.Data;

@Data
public class ApFormDTO {

    private int apFormNo; //양식 번호
    private int apPathNo; //결재선 번호
    private String apFormName; //양식 이름
    private String apFormContent; //양식 내용
    
}
