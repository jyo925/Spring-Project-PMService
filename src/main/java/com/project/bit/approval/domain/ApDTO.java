package com.project.bit.approval.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApDTO {

    private long apNo;
    private long apDocNo;
    private String apApprover; //  결재자ID
    private String apDutyName; //직책
    private long apStep; //몇번째 결재자
    private char apResult; //처리 결과 0:미결 1: 승인 2:반려
    private String apComment; //결재 의견
    private Date apReceiveDate; //결재수신일자
    private Date apSignDate; // 결재처리일자
    private String userName;



    //결재선에서 필요한 정보
    //결재자의 아이디, 이름, 직책, 처리결과, 처리일시

}
