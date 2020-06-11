package com.project.bit.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
		System.err.println(projectTaskGroup);
		projectTaskService.putProjectTaskGroup(projectTaskGroup);
	}
}
