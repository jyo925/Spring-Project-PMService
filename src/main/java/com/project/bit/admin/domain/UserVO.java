package com.project.bit.admin.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class UserVO {

	private String userId;
	private String userPw;
	private String userName;
	private String positionName;
	private int positionCode;
	private String teamName;
	private int teamCode;
	private String userEmail;
	private String userPhone;
	private Date userRegitDate;

}
