package com.project.bit.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bit.project.domain.ProjectTaskDTO;
import com.project.bit.project.domain.ProjectTaskGroupDTO;
import com.project.bit.project.domain.ProjectTaskStatusDTO;
import com.project.bit.project.mapper.ProjectTaskMapper;

@Service
public class ProjectTaskServiceImpl implements ProjectTaskService {

	@Autowired
	private ProjectTaskMapper projectTaskMapper;
	
	/*
	 * @Override public List<ProjectTaskDTO> getProjectTask(String projectId) {
	 * return projectTaskMapper.selectProjectTask(projectId); }
	 */

	@Override
	public List<ProjectTaskGroupDTO> getProjectTaskGroup(String taskGroupId) {
		return projectTaskMapper.selectProjectTaskGroup(taskGroupId);
	}

	@Override
	public void putProjectTaskGroup(ProjectTaskGroupDTO projectTaskGroupDTO) {
		projectTaskMapper.updateProjectTaskGroup(projectTaskGroupDTO);
		
	}

	@Override
	public void postProjectTaskGroup(ProjectTaskGroupDTO projectTaskGroupDTO) {
		projectTaskMapper.insertProjectTaskGroup(projectTaskGroupDTO);		
	}

	@Override
	public void removeProjectTaskGroup(String groupId) {
		projectTaskMapper.deleteProjectTaskGroup(groupId);
		
	}

	@Override
	public List<ProjectTaskStatusDTO> getTaskStatus() {
		return projectTaskMapper.selectTaskStatus();
	}

}
