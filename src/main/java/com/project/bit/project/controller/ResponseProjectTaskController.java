package com.project.bit.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bit.project.domain.ProjectTaskDTO;
import com.project.bit.project.domain.ProjectTaskGroupDTO;
import com.project.bit.project.domain.ProjectTaskManagerDTO;
import com.project.bit.project.service.ProjectTaskService;

@RestController
@RequestMapping("/task")
public class ResponseProjectTaskController {

	@Autowired
	private ProjectTaskService projectTaskService;

	// 프로젝트 작업명 수정
	@PutMapping("/groupName/update")
	public void putProjectTaskGroup(ProjectTaskGroupDTO projectTaskGroup) {
		// System.err.println(projectTaskGroup);
		projectTaskService.putProjectTaskGroup(projectTaskGroup);
	}

	// 프로젝트 작업 그룹 등록
	@PostMapping("/groupAdd")
	public void postProjectTaskGroup(ProjectTaskGroupDTO projectTaskGroup) {
		System.err.println(projectTaskGroup);
		projectTaskService.postProjectTaskGroup(projectTaskGroup);
	}

	// 프로젝트 작업 그룹 삭제
	@DeleteMapping("/groupRemove/{groupId}")
	public void deleteProjectTaskGroup(@PathVariable String groupId) {
		projectTaskService.removeProjectTaskGroup(groupId);
	}
	
	
	
	

	// 프로젝트 작업 등록
	@PostMapping("/add")
	public void postProjectTask(ProjectTaskDTO projectTaskDTO) {
		// System.err.println(projectTaskDTO);
		projectTaskService.postProjectTask(projectTaskDTO);
	}

	@PutMapping("/update")
	public void putProjectTask(ProjectTaskDTO projectTaskDTO) {
		System.err.println(projectTaskDTO);
		projectTaskService.putProjectTask(projectTaskDTO);
	}

	@DeleteMapping("/remove/{taskCode}")
	public void removeProjectTask(@PathVariable String taskCode) {
		projectTaskService.removeProjectTask(taskCode);
	}
	
	@PutMapping("/status/update")
	public void putProjectTaskStatus(String taskCode) {
		projectTaskService.putProjectTaskStatus(taskCode, "taskstatus200");
	}
	
	@PutMapping("/status/back")
	public void putProjectTaskStatusBack(String taskCode) {
		projectTaskService.putProjectTaskStatus(taskCode, "taskstatus300");
	}
	
	
	

	@PostMapping("/manager/add")
	public void postProjectTaskManager(@RequestBody List<ProjectTaskManagerDTO> managers) {
		System.err.println(managers);
		for (ProjectTaskManagerDTO manager : managers) {
			projectTaskService.postProjectTaskManager(manager);
		}
	}
	
	@DeleteMapping("/manager/delete/{managerCode}")
	public void removeProjectTaskManager(@PathVariable String managerCode) {
		System.err.println(managerCode);
		projectTaskService.removeProjectTaskManager(managerCode);
	}

}
