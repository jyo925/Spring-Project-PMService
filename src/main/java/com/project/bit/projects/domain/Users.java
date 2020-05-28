package com.project.bit.projects.domain;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class Users {

	private String USER_ID;
	private String USER_PW;
	private String USER_NAME;
	private int POSITION_CODE;
	private int TEAM_CODE;
	private String USER_EMAIL;
	private String USER_PHONE;
	private Date USER_REGITDATE;
	private String DUTY_CODE;
	
	
	public Users() {
		// TODO Auto-generated constructor stub
	}

}
