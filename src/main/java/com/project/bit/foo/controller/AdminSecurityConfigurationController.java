package com.project.bit.foo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.bit.foo.mapper.AuthorityMapper;
import com.project.bit.foo.mapper.DutyMapper;
import com.project.bit.foo.service.AuthorityService.AuthorityService;

@Controller
public class AdminSecurityConfigurationController {

	private final AuthorityMapper authorityMapper;
	private final DutyMapper dutyMapper;
	private final AuthorityService authorityService;
	
	public AdminSecurityConfigurationController(AuthorityMapper authorityMapper,
			       								DutyMapper dutyMapper,
			       								AuthorityService authorityService) {
		this.authorityMapper = authorityMapper;
		this.dutyMapper = dutyMapper;
		this.authorityService = authorityService;
	}
	
	@GetMapping("/authorityConfig/{projectCode}")
	public String authorityConfig(@PathVariable String projectCode, Model model) {
		model.addAttribute("duty", dutyMapper.getDutys());
		model.addAttribute("members", authorityMapper.selectProjectMembers(projectCode));
		return "projectMembers/authorityConfig";
	}
	
	@PostMapping("/authorityConfig/{projectCode}")
	public String changeAuthority (@PathVariable String projectCode, @RequestParam String data) {
		authorityService.editMembers(data);
		return "redirect:/authorityConfig/{projectCode}";
	}

}
