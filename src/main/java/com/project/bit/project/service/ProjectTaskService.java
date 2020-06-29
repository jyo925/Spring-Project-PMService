package com.project.bit.project.service;

import java.util.List;

import com.project.bit.project.domain.ProjectTaskDTO;
import com.project.bit.project.domain.ProjectTaskGroupDTO;
import com.project.bit.project.domain.ProjectTaskManagerDTO;
import com.project.bit.project.domain.ProjectTaskStatusDTO;
import com.project.bit.project.domain.ProjectTaskVO;
import com.project.bit.project.domain.ProjectOutputTypeDTO;

public interface ProjectTaskService {
	//public List<ProjectTaskDTO> getProjectTask(String projectId);
	public List<ProjectTaskGroupDTO> getProjectTaskGroup(String taskGroupId);
	void putProjectTaskGroup(ProjectTaskGroupDTO projectTaskGroupDTO);
	void postProjectTaskGroup(ProjectTaskGroupDTO projectTaskGroupDTO);
	void removeProjectTaskGroup(String groupId);
	
	void postProjectTask(ProjectTaskDTO projectTaskDTO);
	void putProjectTask(ProjectTaskDTO projectTaskDTO);
	void removeProjectTask(String taskCode);
	void putProjectTaskStatus(String taskCode, String statusCode);
	
	void postProjectTaskManager(ProjectTaskManagerDTO manager);
	
	List<ProjectTaskStatusDTO> getTaskStatus();
}
