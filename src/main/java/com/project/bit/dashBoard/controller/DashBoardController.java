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
import com.project.bit.project.domain.ProjectInfoVO;
import com.project.bit.project.service.ProjectDetailService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

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

	/* dashBoard User */
	@GetMapping("/dIndex")
	public String dashBoardUserCont(Model model, Principal principal) {
		model.addAttribute("userStatusCount", dashBoardUserService.getDashBoardUserCount(principal.getName()));
		model.addAttribute("userTaskList", dashBoardUserService.getMyTaskList(principal.getName()));
		model.addAttribute("userOutputList", dashBoardUserService.getMyOutputList(principal.getName()));
		return "dashBoard/dashBoardUser";
	}

	/* Response */
	@GetMapping("/monthly")
	@ResponseBody /**/
	public ResponseEntity monthly() {
		return new ResponseEntity(dashBoardAllService.getKeyProject(), HttpStatus.OK);
	}
	@GetMapping("/search")
	@ResponseBody
	public ResponseEntity search(String keyword) {
		return new ResponseEntity(dashBoardDetailService.searchProjectList(keyword), HttpStatus.OK);
	}

	@GetMapping("/project/detail/info/{projectCode}")
	@ResponseBody
	public ProjectInfoVO getProjectInfo(@PathVariable String projectCode){
		return projectDetailService.getProjectInfo(projectCode);
	}


	
}
