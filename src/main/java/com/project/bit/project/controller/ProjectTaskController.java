package com.project.bit.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.bit.project.domain.ProjectOutputTypeDTO;
import com.project.bit.project.domain.ProjectTaskStatusDTO;
import com.project.bit.project.service.ProjectMemberService;
import com.project.bit.project.service.ProjectOutputService;
import com.project.bit.project.service.ProjectTaskService;

@Controller
public class ProjectTaskController {
	
	@Autowired
	private ProjectTaskService projectTaskService;
	
	@Autowired
	private ProjectMemberService projectMemberService;
	
	@Autowired
	private ProjectOutputService projectOutputService;
	
	// 프로젝트 작업 페이지로 이동
	@GetMapping("/projectTask/{projectCode}")
	public String getProjectTask(@PathVariable String projectCode, Model model) {
		model.addAttribute("taskGroupList", projectTaskService.getProjectTaskGroup(projectCode));
		model.addAttribute("projectMembers", projectMemberService.getProjectMember(projectCode));
		return "project/projectTask";
	}
	
	
	
	// 프로젝트 작업 상태 
	@ModelAttribute("taskStatusList")
	public List<ProjectTaskStatusDTO> getTaskStatusList(){
		return projectTaskService.getTaskStatus();
	}
	
	@ModelAttribute("outputTypeList")
	public List<ProjectOutputTypeDTO> getOutputTypeList(){
		return projectOutputService.getOutputType();
	}
}
