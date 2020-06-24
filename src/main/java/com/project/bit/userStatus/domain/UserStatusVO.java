package com.project.bit.userstatus.domain;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Alias("UserStatusVO")
public class UserStatusVO {

    private String user_id;
    private String user_name;
    private String user_email;
    private String user_phone;
    private int taskCountTotal;
    private int taskCountProcessing;  //진행중
    private int taskCountComplete; //완료
    private int taskCountStandBy; //대기


}
