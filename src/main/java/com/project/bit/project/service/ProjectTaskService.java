package com.project.bit.project.service;

import java.util.List;

import com.project.bit.project.domain.ProjectTaskDTO;
import com.project.bit.project.domain.ProjectTaskGroupDTO;
import com.project.bit.project.domain.ProjectTaskStatusDTO;

public interface ProjectTaskService {
	//public List<ProjectTaskDTO> getProjectTask(String projectId);
	public List<ProjectTaskGroupDTO> getProjectTaskGroup(String taskGroupId);
	void putProjectTaskGroup(ProjectTaskGroupDTO projectTaskGroupDTO);
	void postProjectTaskGroup(ProjectTaskGroupDTO projectTaskGroupDTO);
	void removeProjectTaskGroup(String groupId);
	List<ProjectTaskStatusDTO> getTaskStatus();
}
