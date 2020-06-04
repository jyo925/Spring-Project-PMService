package com.project.bit.projects.domain;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class Users {

	private String userId;
	private String userPw;
	private String userName;
	private int positionCode;
	private int teamCode;
	private String userEmail;
	private String userPhone;
	private Date userRregitdate;
	private String dutyCode;
	
	
	public Users() {
		// TODO Auto-generated constructor stub
	}

}
