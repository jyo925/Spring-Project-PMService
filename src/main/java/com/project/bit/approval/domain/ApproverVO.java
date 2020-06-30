package com.project.bit.approval.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
public class ApproverVO {

    private String userId;
    private String userName;
    private String dutyName;

}
