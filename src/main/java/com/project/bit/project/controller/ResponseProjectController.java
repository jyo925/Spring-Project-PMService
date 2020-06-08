package com.project.bit.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bit.project.domain.ProjectIssueStatusVO;
import com.project.bit.project.domain.ProjectTaskStatusVO;
import com.project.bit.project.domain.ProjectVO;
import com.project.bit.project.service.ProjectService;

@RestController
@RequestMapping("/project")
public class ResponseProjectController {

	@Autowired
	private ProjectService projectService;
	
	@GetMapping("/search")
	public List<ProjectVO> getProjectListBySearch(String typeCode, String projectName){
		return projectService.getProjectSearch(typeCode, projectName);
	}
	
	@GetMapping("/detail/taskChart")
	public List<ProjectTaskStatusVO> getProjectTaskChart(String projectId){
		return projectService.getProjectTaskStatusCount(projectId);
	}
	
	@GetMapping("/detail/issueChart")
	public List<ProjectIssueStatusVO> getProjectIssueChart(String projectId){
		return projectService.getProjectIssueStatusCount(projectId);
	}
}
