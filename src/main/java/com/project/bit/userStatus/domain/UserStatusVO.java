package com.project.bit.userStatus.domain;

import lombok.*;

//@Data
//@AllArgsConstructor
@Getter @Setter @ToString
public class UserStatusVO {

    private String userId;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String projectName;
    private int taskCountTotal;
    private int taskCountProcessing;  //진행중
    private int taskCountComplete; //완료
    private int taskCountStandBy; //대기


}
