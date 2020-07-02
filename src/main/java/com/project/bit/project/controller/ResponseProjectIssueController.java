package com.project.bit.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.bit.project.domain.ProjectIssueDTO;
import com.project.bit.project.domain.ProjectTaskGroupDTO;
import com.project.bit.project.service.ProjectIssueService;
import com.project.bit.project.service.ProjectTaskService;

@RestController
@RequestMapping("/issue")
public class ResponseProjectIssueController {

	@Autowired
	private ProjectTaskService projectTaskService;
	@Autowired
	private ProjectIssueService projectIssueService;

	@GetMapping("/taskList/{projectId}")
	public List<ProjectTaskGroupDTO> getProjectTaskList(@PathVariable String projectId) {
		return projectTaskService.getProjectTaskGroup(projectId);
	}

	@PutMapping("/update")
	public void putProjectIssue(ProjectIssueDTO projectIssueDTO) {
		System.err.println(projectIssueDTO);
		projectIssueService.putProjectIssue(projectIssueDTO);
	}

	@PostMapping(value = "/update/file", produces = MediaType.APPLICATION_JSON_VALUE)
	public void putProjectIssueFile(MultipartFile issueFile, String issueCode, String issueFilePath) {
		projectIssueService.putProjectFile(issueFile, issueCode, issueFilePath);
	}

	@GetMapping("/search")
	public List<ProjectIssueDTO> getProjectIssueSearch(String issueType, String issueName, String projectCode) {
		return projectIssueService.getProjectIssueSearch(issueType, issueName, projectCode);
	}

	@DeleteMapping("/delete/file/{issueCode}")
	public void removeProjectIssueFile(@PathVariable String issueCode, String issuePath) {
		projectIssueService.removeProjectFile(issueCode, issuePath);
	}

}
