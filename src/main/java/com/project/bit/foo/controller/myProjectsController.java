package com.project.bit.foo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.bit.foo.domain.projectView.MyProjectsVO;
import com.project.bit.foo.service.myProjectsViewService.MyProjectsVOServise;
import com.project.bit.foo.service.projectStatus.ProjectStatusGetServise;

@Controller
public class myProjectsController {

	private final MyProjectsVOServise projectsVOServise;
	private final ProjectStatusGetServise projectStatusGetServise;
	
	public myProjectsController(MyProjectsVOServise projectsVOServise, ProjectStatusGetServise projectStatusGetServise) {
		this.projectsVOServise = projectsVOServise;
		this.projectStatusGetServise = projectStatusGetServise;
	}
	
	@GetMapping("/myProjects")
	public String myProjects(Model model, Principal principal, String today) {
		List<MyProjectsVO> myProjects = projectsVOServise.getProjects(principal.getName(), today);
		model.addAttribute("myProjects", myProjects);
		model.addAttribute("projectStatusNames", projectStatusGetServise.selectAllStatusNames());
		return "myPage/myProjects";
	}

}
