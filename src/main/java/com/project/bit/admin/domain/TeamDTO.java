package com.project.bit.admin.domain;

import lombok.Data;

@Data
public class TeamDTO {
	
	private int TEAM_CODE;
	private String TEAM_NAME;
	private String TEAM_UPPER;
	private int TEAM_SEQ;
	private String TEAM_USE;

	public TeamDTO() {
		// TODO Auto-generated constructor stub
	}

}
