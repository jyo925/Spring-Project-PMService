package com.project.bit.admin.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class UserDTO {

	private String userId;
	private String userPw;
	private String userName;
	private int positionCode;
	private int teamCode;
	private String userEmail;
	private String userPhone;
	private Date userRregitdate;
	private String dutyCode;


	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

}
