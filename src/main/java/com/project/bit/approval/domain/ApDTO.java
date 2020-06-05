package com.project.bit.approval.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ApDTO {

    private Long apNo;
    private Long apDocNo;
    private int apStep; //몇번째 결재자
    private int apResult; //처리 결과 0:미결 1: 승인 -1:반려
    private String apComment; //결재 의견
    private Date apReceiveDate; //결재수신일자
    private Date apSignDate; // 결재처리일자
    private String approver; // 결재자ID
    private String apDutyName; //직책

    //결재선에서 필요한 정보
    //결재자의 아이디, 이름, 직책, 처리결과, 처리일시

}
