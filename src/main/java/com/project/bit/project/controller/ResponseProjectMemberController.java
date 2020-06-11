package com.project.bit.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bit.project.domain.ProjectMemberDTO;
import com.project.bit.project.service.ProjectMemberService;

@RestController
@RequestMapping("/projectMember")
public class ResponseProjectMemberController {

	@Autowired
	private ProjectMemberService projectMemberService;
	
	@PostMapping("/add")
	public void postProjectMember(@RequestBody List<ProjectMemberDTO> projectMemberList) {
		for(ProjectMemberDTO member : projectMemberList) {
			projectMemberService.postProjectMember(member);
		}
	}
	
	@RequestMapping("/remove")
	public void removeProjectMember(@RequestBody List<String> projectJoinCode) {
		for(String str : projectJoinCode) {
			projectMemberService.deleteProjectMember(str);
		}
	}

}
