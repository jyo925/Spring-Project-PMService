package com.project.bit.foo.domain.files;

import lombok.Data;

@Data
public class FilesVO {
	
	private String outputCode;
	private String outputName;
	private String projectName;
	private String outputUser;
	private String outputDate;
	private String outputPath;
	private String outputTypeCode;
	private String taskCode;
	private String outputType;
	private String taskName;

	public FilesVO() {
		// TODO Auto-generated constructor stub
	}

}
