package com.project.bit.project.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class ProjectInfoVO {
	private String projectCode;
	private String projectName;
	private String projectSubName;
	private Date projectStart;
	private Date projectFinish;
	private String projectPm;
	private String projectPmo;
	private String projectDescription;
	private int projectPriority;
	private String projectStatusCode;
	private String projectStatusName;
	private String projectTypeCode;
	private String projectTypeName;
	private int projectTaskAccount;
	private int projectMemberAccount;
}
