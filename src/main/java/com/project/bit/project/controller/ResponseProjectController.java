package com.project.bit.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bit.foo.domain.Users;
import com.project.bit.foo.service.UserService;
import com.project.bit.project.domain.ProjectDTO;
import com.project.bit.project.domain.ProjectIssueStatusVO;
import com.project.bit.project.domain.ProjectTaskStatusVO;
import com.project.bit.project.domain.ProjectVO;
import com.project.bit.project.service.ProjectDetailService;
import com.project.bit.project.service.ProjectService;

@RestController
@RequestMapping("/project")
public class ResponseProjectController {

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ProjectDetailService projectDetailService;
	
	@Autowired
	private UserService userService;
	
	/*
	 * @GetMapping("/search") public List<ProjectVO> getProjectListBySearch(String
	 * typeCode, String projectName){ return
	 * projectService.getProjectSearch(typeCode, projectName); }
	 */
	
	@GetMapping("/detail/taskChart")
	public List<ProjectTaskStatusVO> getProjectTaskChart(String projectId){
		return projectDetailService.getProjectTaskStatusCount(projectId);
	}
	
	@GetMapping("/detail/issueChart")
	public List<ProjectIssueStatusVO> getProjectIssueChart(String projectId){
		return projectDetailService.getProjectIssueStatusCount(projectId);
	}
	
	@GetMapping("/team")
	public List<Users> getUserByTeam(int teamCode){
		return userService.selectUserByTeam(teamCode);
	}
	
	@PutMapping("{projectCode}")
	public int putProject(@PathVariable String projectCode, ProjectDTO projectDTO) {
		return projectService.putProject(projectDTO);
	}
}
