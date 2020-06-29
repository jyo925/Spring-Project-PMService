package com.project.bit.dashBoard.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.bit.dashBoard.domain.IssueStatusCountVO;
import com.project.bit.dashBoard.domain.MonthlyProjectCountVO;
import com.project.bit.dashBoard.domain.ProjectStatusCountVO;
import com.project.bit.dashBoard.domain.ProjectTypeCountVO;
import com.project.bit.dashBoard.domain.TaskStatusCountVO;
import com.project.bit.dashBoard.service.DashBoardAllService;
import com.project.bit.dashBoard.service.DashBoardDetailService;
import com.project.bit.dashBoard.service.DashBoardUserService;
import com.project.bit.project.domain.ProjectDTO;
import com.project.bit.project.domain.ProjectInfoVO;
import com.project.bit.project.service.ProjectDetailService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class DashBoardController {
	
	private DashBoardDetailService dashBoardDetailService;
	private DashBoardAllService dashBoardAllService;
	private DashBoardUserService dashBoardUserService;
	private ProjectDetailService projectDetailService;
	
	@GetMapping("/dashBoardDetail")
	public String goDashBoard(Model model) {
		List<ProjectDTO> lists = dashBoardDetailService.findProjectList();
		model.addAttribute("projectNameList", lists);
		model.addAttribute("defaultProject", lists.get(0));
		return "dashBoard/dashBoardDetail";
	}
	
	@GetMapping("/dashBoardDetail/{projectCode}")
	public String goDashBoardDetail(@PathVariable String projectCode, Model model) {
		List<ProjectDTO> lists = dashBoardDetailService.findProjectList();
		ProjectDTO projectOne = new ProjectDTO();
		for(ProjectDTO project : lists) {
			if(project.getProjectCode().equals(projectCode))
				projectOne = project;
		}
		model.addAttribute("projectNameList", lists);
		model.addAttribute("defaultProject", projectOne);
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


	/* Chart */
	@GetMapping("/dashBoard/chart/task")
	@ResponseBody
	public List<TaskStatusCountVO> TaskStatusChart(Principal principal) {
		return dashBoardUserService.getTaskStatusCount(principal.getName());
	}

	@GetMapping("/dashBoard/chart/issue")
	@ResponseBody
	public List<IssueStatusCountVO> IssueStatusChart(Principal principal) {
		return dashBoardUserService.getIssueStatusCount(principal.getName());
	}

	@GetMapping("/dashBoard/chart/projectAll")
	@ResponseBody
	public List<ProjectStatusCountVO> ProjectAllStatusChart() {
		return dashBoardAllService.getProjectAllStatus();
	}
	
	@GetMapping("/dashBoard/chart/projectAllByType")
	@ResponseBody
	public List<ProjectTypeCountVO> projectAllTypeChart(){
		return dashBoardAllService.getProjectAllType();
	}

	@GetMapping("/dashBoard/chart/issueAll")
	@ResponseBody
	public List<IssueStatusCountVO> IssueAllStatusChart() {
		return dashBoardAllService.getIssueAllStatus();
	}

	@GetMapping("/dashBoard/chart/monthlyProject")
	@ResponseBody
	public List<MonthlyProjectCountVO> MonthlyProjectChart() {
		return dashBoardAllService.getMonthlyProject();
	}
	
}
