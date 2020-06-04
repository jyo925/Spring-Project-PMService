package com.project.bit.project.domain;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
public class ProjectDTO {
	protected String projectCode;
	protected String projectName;
	protected String projectSubName;
	protected Date projectStart;
	protected Date projectFinish;
	protected String projectPm;
	protected String projectPmo;
	protected String projectDescription;
	protected int projectPriority;
	protected String projectStatusCode;
	protected String projectStatusName;
	protected String projectTypeCode;
	protected String projectTypeName;
}

