package com.project.bit.project.service;

import java.util.List;

import com.project.bit.project.domain.ProjectTaskDTO;
import com.project.bit.project.domain.ProjectTaskGroupDTO;

public interface ProjectTaskService {
	//public List<ProjectTaskDTO> getProjectTask(String projectId);
	public List<ProjectTaskGroupDTO> getProjectTaskGroup(String taskGroupId);
	void putProjectTaskGroup(ProjectTaskGroupDTO projectTaskGroupDTO);
}
