package com.project.bit.foo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.bit.foo.domain.projectView.ProjectsMembersVO;
import com.project.bit.foo.mapper.AuthorityMapper;

@Controller
public class AdminSecurityConfigurationController {

	private final AuthorityMapper authorityMapper;
	
	public AdminSecurityConfigurationController(AuthorityMapper authorityMapper) {
		this.authorityMapper = authorityMapper;
	}
	
	@GetMapping("/authorityConfig/{projectCode}")
	public String authorityConfig(@PathVariable String projectCode, Model model) {
		List<ProjectsMembersVO> members = authorityMapper.selectProjectMembers(projectCode);
		System.out.println(members);
		model.addAttribute("members", authorityMapper.selectProjectMembers(projectCode));
		return "projectMembers/authorityConfig";
	}

}
