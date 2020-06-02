package com.project.bit.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.bit.project.service.ProjectService;

@Controller
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	
	@GetMapping("/projectListAll")
	public String getProjectListAll(Model model) {
		model.addAttribute("projectList", projectService.getProjectListAll());
		return "getProjectListAll";
	}
}
