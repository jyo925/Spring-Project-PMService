package com.project.bit.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.bit.project.domain.ProjectDTO;
import com.project.bit.project.domain.ProjectStatusDTO;
import com.project.bit.project.domain.ProjectTypeDTO;
import com.project.bit.project.service.ProjectService;

@Controller
public class ProjectController {

	@Autowired
	private ProjectService projectService;	
	
	// main으로 이동
	@GetMapping("/index")
	public String goIndex() {
		return "index";
	}
	
	// 프로젝트 리스트
	@GetMapping("/projectList")
	public String getProjectList(Model model) {
		model.addAttribute("projectList", projectService.getProjectListAll());
		return "/project/getProjectList";
	}
	
	// 프로젝트 등록
	@PostMapping("/projectInsert")
	public String postProject(ProjectDTO projectDTO) {
		projectService.postProject(projectDTO);
		return "redirect:/projectList";
	}	
	
	
	
	
	// 프로젝트 유형 리스트
	@ModelAttribute("projectTypeList")
	public List<ProjectTypeDTO> getProjectType(){
		return projectService.getProjectTypeAll();
	}
	
	// 프로젝트 상태 리스트
	@ModelAttribute("projectStatus")
	public List<ProjectStatusDTO> getProjectStatus(){
		return projectService.getProjectStatusListAll();
	}

}
