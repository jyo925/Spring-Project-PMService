package com.project.bit.dashBoard.controller;

import com.project.bit.project.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.bit.dashBoard.service.DashBoardDetailService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class DashBoardController {
	
	private DashBoardDetailService dashBoardDetailService;
	private ProjectService projectService;
	
	@GetMapping("/dashBoard")
	public String goDashBoard(Model model) {
		model.addAttribute("projectNameList", dashBoardDetailService.findProjectList());
		return "dashBoard/dashBoardDetail";
	}

	@GetMapping("/dashBoardAll")
	public String getProjectList(Model model) {
		model.addAttribute("projectList", projectService.getProjectListAll());
		return "dashBoard/dashBoardAll";
	}
	
}
