package com.project.bit.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.bit.project.domain.ProjectTaskDTO;
import com.project.bit.project.domain.ProjectTaskGroupDTO;
import com.project.bit.project.domain.ProjectTaskManagerDTO;
import com.project.bit.project.domain.ProjectTaskStatusDTO;
import com.project.bit.project.domain.ProjectTaskVO;

@Mapper
public interface ProjectTaskMapper {
	// public List<ProjectTaskDTO> selectProjectTask(String projectId);
	public List<ProjectTaskGroupDTO> selectProjectTaskGroup(String taskGroupId);
	void updateProjectTaskGroup(ProjectTaskGroupDTO projectTaskGroupDTO);
	void insertProjectTaskGroup(ProjectTaskGroupDTO projectTaskGroupDTO);
	void deleteProjectTaskGroup(String groupId);
	
	void insertProjectTask(ProjectTaskDTO projectTaskDTO);
	void updateProjectTask(ProjectTaskDTO projectTaskDTO);
	void deleteProjectTask(String taskCode);
	void updateProjectTaskStatus(@Param("taskCode") String taskCode, @Param("statusCode") String statusCode);
	
	void insertProjectTaskManager(ProjectTaskManagerDTO manager);
	
	List<ProjectTaskStatusDTO> selectTaskStatus();
}
