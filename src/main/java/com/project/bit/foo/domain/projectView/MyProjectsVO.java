package com.project.bit.foo.domain.projectView;

import lombok.Data;

@Data
public class MyProjectsVO {
	
	private String projectName;
	private String projectPmo;
	private String projectStatus;
	private String projectStart;
	private String projectFinish;
	private String taskName;

	public MyProjectsVO() {
		// TODO Auto-generated constructor stub
	}

}
