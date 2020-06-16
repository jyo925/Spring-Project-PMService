package com.project.bit.dashBoard.controller;

import com.project.bit.dashBoard.mapper.DashBoardAllMapper;
import com.project.bit.dashBoard.service.DashBoardAllService;
import com.project.bit.project.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.bit.dashBoard.service.DashBoardDetailService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class DashBoardController {
	
	private DashBoardDetailService dashBoardDetailService;
	private DashBoardAllService dashBoardAllService;
	private DashBoardAllMapper dashBoardAllMapper;
	
	@GetMapping("/dashBoardDetail")
	public String goDashBoard(Model model) {
		model.addAttribute("projectNameList", dashBoardDetailService.findProjectList());
		return "dashBoard/dashBoardDetail";
	}

	/* dashBoard All Start */
	@GetMapping("/dashBoardAll")
	public String dashBoardSection(Model model) {
		model.addAttribute("keyProjectList", dashBoardAllService.getKeyProject());
		model.addAttribute("projectStatusCount", dashBoardAllService.getProjectAllStatusCount());
		return "dashBoard/dashBoardAll";
	}

	/**/
	@GetMapping("/monthly")
	@ResponseBody /**/
	public ResponseEntity monthly() {
		return new ResponseEntity(dashBoardAllService.getKeyProject(), HttpStatus.OK);
	}



	
}
