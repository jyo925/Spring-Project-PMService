package com.project.bit.project.domain;

import java.util.List;

import lombok.Data;

@Data
public class ProjectTaskGroupDTO {
	protected String taskGroupCode;
	protected String taskGroupName;
	protected String taskGroupDescription;
	protected String projectCode;
	private List<ProjectTaskDTO> projectTasks;
}
