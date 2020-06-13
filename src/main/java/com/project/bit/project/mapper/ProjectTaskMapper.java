package com.project.bit.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.bit.project.domain.ProjectTaskGroupDTO;
import com.project.bit.project.domain.ProjectTaskStatusDTO;

@Mapper
public interface ProjectTaskMapper {
	// public List<ProjectTaskDTO> selectProjectTask(String projectId);
	public List<ProjectTaskGroupDTO> selectProjectTaskGroup(String taskGroupId);
	void updateProjectTaskGroup(ProjectTaskGroupDTO projectTaskGroupDTO);
	void insertProjectTaskGroup(ProjectTaskGroupDTO projectTaskGroupDTO);
	void deleteProjectTaskGroup(String groupId);
	
	List<ProjectTaskStatusDTO> selectTaskStatus();
}
