package com.project.bit.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bit.project.domain.ProjectTaskGroupDTO;
import com.project.bit.project.service.ProjectTaskService;

@RestController
@RequestMapping("/task")
public class ResponseProjectTaskController {
	
	@Autowired
	private ProjectTaskService projectTaskService;
	
	@PutMapping("/groupName/update")
	public void putProjectTaskGroup(ProjectTaskGroupDTO projectTaskGroup) {
		//System.err.println(projectTaskGroup);
		projectTaskService.putProjectTaskGroup(projectTaskGroup);
	}
	
	@PostMapping("/groupAdd")
	public void postProjectTaskGroup(ProjectTaskGroupDTO projectTaskGroup) {
		System.err.println(projectTaskGroup);
		projectTaskService.postProjectTaskGroup(projectTaskGroup);
	}
	
	@DeleteMapping("/groupRemove/{groupId}")
	public void deleteProjectTaskGroup(@PathVariable String groupId) {
		projectTaskService.removeProjectTaskGroup(groupId);
	}
	
}
