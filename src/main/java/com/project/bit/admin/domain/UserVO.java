package com.project.bit.admin.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Date;

@Data

public class UserVO {

	private String userId;
	private String userPw;
	private String userName;
	private String positionName;
	private String teamName;
	private String userEmail;
	private String userPhone;
	private Date userRegitDate;

}
