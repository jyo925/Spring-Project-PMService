package com.project.bit.project.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class ProjectOutputDTO {
	private String outputCode;
	private String outputName;
	private String outputUser;
	private Date outputDate;
	private String outputPath;
	private String outputTypeCode;
	private String outputTypeName;
	private String taskCode;
}
