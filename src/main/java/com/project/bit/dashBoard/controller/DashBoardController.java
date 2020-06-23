package com.project.bit.dashBoard.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.bit.dashBoard.mapper.DashBoardAllMapper;
import com.project.bit.dashBoard.service.DashBoardAllService;
import com.project.bit.dashBoard.service.DashBoardDetailService;
import com.project.bit.project.domain.ProjectInfoVO;
import com.project.bit.project.service.ProjectDetailService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class DashBoardController {
	
	private DashBoardDetailService dashBoardDetailService;
	private DashBoardAllService dashBoardAllService;
	private DashBoardAllMapper dashBoardAllMapper;
	private ProjectDetailService projectDetailService;
	
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
	
	@GetMapping("/project/detail/info/{projectCode}")
	@ResponseBody
	public ProjectInfoVO getProjectInfo(@PathVariable String projectCode){
		return projectDetailService.getProjectInfo(projectCode);
	}

	/**/
	@GetMapping("/monthly")
	@ResponseBody /**/
	public ResponseEntity monthly() {
		return new ResponseEntity(dashBoardAllService.getKeyProject(), HttpStatus.OK);
	}



	
}
