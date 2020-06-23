package com.project.bit.project.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class ProjectIssueDTO {
	private String issueCode;
	private String issueName;
	private String issueDescription;
	private String issueUserId;
	private Date issueDate;
	private String issueActionId;
	private Date issueActionDate;
	private String issueTypeCode;
	private String issueTypeName;
	private String issueFileName;
	private String issueFilePath;
	private String taskCode;
	private String projectName;
	
}
