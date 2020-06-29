package com.project.bit.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.bit.project.domain.ProjectMemberDTO;
import com.project.bit.project.domain.ProjectTaskVO;
import com.project.bit.project.service.ProjectMemberService;

@RestController
public class ResponseProjectMemberController {

	@Autowired
	private ProjectMemberService projectMemberService;
	
	@PostMapping("/projectMember/add")
	public void postProjectMember(@RequestBody List<ProjectMemberDTO> projectMemberList) {
		
		for(ProjectMemberDTO member : projectMemberList) {
			projectMemberService.postProjectMember(member);
		}
	}
	
	@DeleteMapping("/projectMember/remove")
	public void removeProjectMember(@RequestBody List<String> projectJoinCode) {
		
		for(String str : projectJoinCode) {
			System.err.println(str);
			projectMemberService.deleteProjectMember(str);
		}
	}
	
	@GetMapping("/projectMemberInfo/gantt")
	public List<ProjectTaskVO> getProjectMemberGantt(String projectCode){
		System.err.println(projectCode);
		return projectMemberService.getProjectMemberGantt(projectCode);
	}

}
