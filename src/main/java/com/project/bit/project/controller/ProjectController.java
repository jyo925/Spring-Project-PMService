package com.project.bit.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.bit.project.domain.ProjectTypeDTO;
import com.project.bit.project.service.ProjectService;

@Controller
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	
	@GetMapping("/projectList")
	public String getProjectListAll(Model model) {
		model.addAttribute("projectList", projectService.getProjectListAll());
		return "/project/getProjectList";
	}
	
	@GetMapping("/goProjectAdd")
	public String goProjectAdd() {
		return "/project/projectInsert";
	}
	
	@ModelAttribute("projectTypeList")
	public List<ProjectTypeDTO> getProjectType(){
		return projectService.getProjectTypeAll();
	}
}
