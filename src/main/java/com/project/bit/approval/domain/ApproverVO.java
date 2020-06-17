package com.project.bit.approval.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
public class ApproverVO {

    private String userId; //user_id
    private String userName; //user_name
    private String dutyName;

}
