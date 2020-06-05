package com.project.bit.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.bit.project.domain.ProjectMemberVO;
import com.project.bit.project.domain.ProjectStatusDTO;
import com.project.bit.project.service.ProjectMemberService;
import com.project.bit.project.service.ProjectService;

@Controller
public class ProjectDetailController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ProjectMemberService projectMemberService;
	
	// 프로젝트 상세 차트
	@RequestMapping("/project/{projectId}")
	public String getProjectDetailChart(@PathVariable String projectId, Model model) {
		model.addAttribute("project", projectService.getProjectInfo(projectId));
		return "/project/projectDetailChart";
	}
	
	// 프로젝트 상세 정보
	@GetMapping("/project/detail/{projectId}")
	public String getProjectDetail(@PathVariable String projectId, Model model) {
		model.addAttribute("project", projectService.getProjectOne(projectId));
		return "/project/projectDetail";
	}
	
	@ModelAttribute("projectStatus")
	public List<ProjectStatusDTO> getProjectStatus(){
		return projectService.getProjectStatusListAll();
	}

}
