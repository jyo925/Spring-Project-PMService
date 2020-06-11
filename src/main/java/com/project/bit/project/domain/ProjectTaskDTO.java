package com.project.bit.project.domain;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class ProjectTaskDTO {
	protected String taskCode;
	protected String taskName;
	protected Date taskStart;
	protected Date taskFinish;
	protected String taskDescription;
	protected String taskGroupCode;
	protected String taskGroupName;
	protected String taskStatusCode;
	protected String taskStatusName;
	private List<ProjectTaskManagerDTO> projectTaskManagers;
}
