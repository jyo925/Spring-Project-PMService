package com.project.bit.foo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.bit.foo.domain.projectView.MyProjectsVO;
import com.project.bit.foo.service.myProjectsViewService.MyProjectsVOServise;

@Controller
public class myProjectsController {

	private final MyProjectsVOServise projectsVOServise;
	
	public myProjectsController(MyProjectsVOServise projectsVOServise) {
		this.projectsVOServise = projectsVOServise;
	}
	
	@GetMapping("/myProjects")
	public String myProjects(Model model, Principal principal, String today) {
		List<MyProjectsVO> myProjects = projectsVOServise.getProjects(principal.getName(), today);
		model.addAttribute("myProjects", myProjects);
		return "myPage/myProjects";
	}

}
