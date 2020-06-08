package com.project.bit.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.bit.project.domain.ProjectDTO;
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
	
	// 프로젝트 등록 페이지로 이동
	@GetMapping("/goProjectAdd")
	public String goProjectAdd(@ModelAttribute ProjectDTO projectDTO) {
		return "/project/projectInsert";
	}
	
	// 프로젝트 등록
	@PostMapping("/projectInsert")
	public String postProject(ProjectDTO projectDTO) {
		System.out.println(projectDTO);
		return "redirect:/projectList";
	}	
	
	
	
	@ModelAttribute("projectTypeList")
	public List<ProjectTypeDTO> getProjectType(){
		return projectService.getProjectTypeAll();
	}

}
