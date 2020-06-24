package com.project.bit.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.bit.foo.domain.Users;
import com.project.bit.foo.service.PositionsService;
import com.project.bit.foo.service.TeamsService;
import com.project.bit.foo.service.UserService;
import com.project.bit.project.domain.PositionDTO;
import com.project.bit.project.domain.ProjectDTO;
import com.project.bit.project.domain.ProjectIssueTypeDTO;
import com.project.bit.project.domain.ProjectOutputTypeDTO;
import com.project.bit.project.domain.ProjectStatusDTO;
import com.project.bit.project.domain.ProjectTaskStatusDTO;
import com.project.bit.project.domain.ProjectTypeDTO;
import com.project.bit.project.domain.TeamDTO;
import com.project.bit.project.service.ProjectDetailService;
import com.project.bit.project.service.ProjectIssueService;
import com.project.bit.project.service.ProjectMemberService;
import com.project.bit.project.service.ProjectOutputService;
import com.project.bit.project.service.ProjectService;
import com.project.bit.project.service.ProjectTaskService;

@Controller
public class ProjectDetailController {
	
	@Autowired	private ProjectService projectService;
	@Autowired	private ProjectDetailService projectDetailService;
	@Autowired	private ProjectMemberService projectMemberService;
	@Autowired	private TeamsService teamsService;
	@Autowired	private PositionsService positionsService;
	@Autowired	private UserService userService;
	@Autowired  private ProjectIssueService projectIssueService;
	@Autowired	private ProjectTaskService projectTaskService;
	@Autowired	private ProjectOutputService projectOutputService;
	
	// 프로젝트 상세 차트
	@RequestMapping("/project/{projectId}")
	public String getProjectDetailChart(@PathVariable String projectId, Model model) {
		model.addAttribute("project", projectDetailService.getProjectInfo(projectId));
		return "/project/projectDetailChart";
	}
	
	// 프로젝트 상세 정보
	@GetMapping("/project/detail/{projectId}")
	public String getProjectDetail(@PathVariable String projectId, Model model) {
		model.addAttribute("project", projectDetailService.getProjectOne(projectId));
		return "/project/projectDetail";
	}
	
	// 프로젝트 등록 페이지로 이동
	@GetMapping("/goProjectAdd")
	public String goProjectAdd(@ModelAttribute ProjectDTO projectDTO, Model model) {
		return "/project/projectInsert";
	}
	
	// 프로젝트 삭제
	@GetMapping("/projectDelete/{projectCode}")
	public String removeProject(@PathVariable String projectCode) {
		projectService.removeProject(projectCode);
		return "redirect:/projectList";
	}
	
	// 프로젝트 멤버 페이지로 이동
	@GetMapping("/projectMember/{projectId}")
	public String getProjectMember(@PathVariable String projectId, Model model) {
		model.addAttribute("project", projectDetailService.getProjectOne(projectId));
		model.addAttribute("projectMembers", projectMemberService.getProjectMember(projectId));
		return "/project/projectMember";
	}
	
	// 프로젝트 이슈 페이지로 이동
	@GetMapping("/projectIssue/{projectId}")
	public String getProjectIssueList(@PathVariable String projectId, Model model) {
		model.addAttribute("project", projectDetailService.getProjectOne(projectId));
		model.addAttribute("issueList", projectIssueService.getProjectIssueList(projectId));
		return "/project/projectIssue";
	}
	
	// 프로젝트 작업 페이지로 이동
	@GetMapping("/projectTask/{projectCode}")
	public String getProjectTask(@PathVariable String projectCode, Model model) {
		model.addAttribute("project", projectDetailService.getProjectOne(projectCode));
		model.addAttribute("taskGroupList", projectTaskService.getProjectTaskGroup(projectCode));
		model.addAttribute("projectMembers", projectMemberService.getProjectMember(projectCode));
		return "project/projectTask";
	}
	
	// 프로젝트 상세 산출물 페이지로 이동
	@GetMapping("/projectOutput/{projectCode}")
	public String goProjectOutput(@PathVariable String projectCode, Model model) {
		model.addAttribute("project", projectDetailService.getProjectOne(projectCode));
		model.addAttribute("projectList", projectService.getProjectListAll());
		model.addAttribute("outputList", projectOutputService.getProjectDetailOutput(projectCode));
		return "project/projectOutput";
	}
	
	// 프로젝트 간트차트 페이지로 이동 
	@GetMapping("/projectGantt/{projectCode}")
	public String goProjectGantt(@PathVariable String projectCode, Model model) {
		model.addAttribute("project", projectDetailService.getProjectOne(projectCode));
		model.addAttribute("taskMember", projectMemberService.getProjectMemberGantt(projectCode));
		return "project/projectGantt";
	}

	
	
	
	// 프로젝트 작업 상태 
	@ModelAttribute("taskStatusList")
	public List<ProjectTaskStatusDTO> getTaskStatusList(){
		return projectTaskService.getTaskStatus();
	}
	
	@ModelAttribute("outputTypeList")
	public List<ProjectOutputTypeDTO> getOutputTypeList(){
		return projectOutputService.getOutputType();
	}

	
	
	
	// 프로젝트 상태 리스트
	@ModelAttribute("projectStatus")
	public List<ProjectStatusDTO> getProjectStatus(){
		return projectService.getProjectStatusListAll();
	}
	
	// 프로젝트 형태 리스트
	@ModelAttribute("projectTypeList")
	public List<ProjectTypeDTO> getProjectType(){
		return projectService.getProjectTypeAll();
	}
	
	// 부서 리스트
	@ModelAttribute("teamList")
	public List<TeamDTO> getTeam(){
		return teamsService.getTeamAll(); 
	}
	
	// 직급 리스트
	@ModelAttribute("positionList")
	public List<PositionDTO> getPosition(){
		return positionsService.getPositionAll();
	}
	
	// 유저 리스트
	@ModelAttribute("userList")
	public List<Users> getUser(){
		return userService.selectAll();
	}
	
	@ModelAttribute("issueTypeList")
	public List<ProjectIssueTypeDTO> getProjectIssueTypeList(){
		return projectIssueService.getProjectIssueTypeList();
	}
	
	@ModelAttribute("outputTypeList")
	public List<ProjectOutputTypeDTO> getProjectOutputTypeList(){
		return projectOutputService.getOutputType();
	}


}
