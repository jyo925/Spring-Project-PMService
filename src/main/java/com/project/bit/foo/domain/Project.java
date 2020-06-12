package com.project.bit.foo.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class Project {
	
	private String projectCode;
	private String projectName;
	private String projectSubname;
	private Date projectStart;
	private Date projectFinish;
	private String projectPm;
	private String projectTypeCode;
	private String projectPmo;
	private String projectDescroption;
	private int projectPriority;
	private String projectStatusCode;

	public Project() {
		// TODO Auto-generated constructor stub
	}

}
