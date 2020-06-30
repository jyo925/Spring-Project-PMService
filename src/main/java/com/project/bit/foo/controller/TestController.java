package com.project.bit.foo.controller;

import java.security.Principal;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.bit.foo.domain.Positions;
import com.project.bit.foo.domain.ProjectMembers;
import com.project.bit.foo.domain.Teams;
import com.project.bit.foo.domain.Users;
import com.project.bit.foo.mapper.EventMapper;
import com.project.bit.foo.service.PositionsServiceImpl;
import com.project.bit.foo.service.ProjectMembersServiceImpl;
import com.project.bit.foo.service.TeamsServiceImpl;
import com.project.bit.foo.service.UserServiceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class TestController {

	private UserServiceImpl userServiceImpl;
	private ProjectMembersServiceImpl projectMembersServiceImpl;
	private PositionsServiceImpl positionsServiceImpl;
	private TeamsServiceImpl teamsServiceImpl;
	private PasswordEncoder bCryptPasswordEncoder;
	private EventMapper eventMapper;

	@GetMapping("/")
	public String Main(Principal principal) {
		if (principal != null) {
			log.info(principal.getName());
			System.err.println(principal.toString());
		}
		
		return "index";
	}

	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}

	@GetMapping("/registration")
	public String registration() {
		return "registration";
	}

	@PostMapping("/registration")
	public String registrationPost(Users user) {
		System.out.println(user);
		user.setUserPw(bCryptPasswordEncoder.encode(user.getUserPw()));
		userServiceImpl.insertUser(user);
		return "redirect:/";
	}

	@GetMapping("/addMembers")
	public String addMembers() {
		return "addMembers";
	}

	@GetMapping("/addDuty")
	public String addDuty() {
		return "addDuty";
	}

	@GetMapping("/addPositions")
	public String addPositions() {
		return "addPositions";
	}

	@GetMapping("/addTeams")
	public String addTeams() {
		return "addTeams";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String loginPost() {
		return "redirect:/";
	}

	@PostMapping("/addMembers")
	public String addMembersPost(ProjectMembers ProjectMembers) {
		projectMembersServiceImpl.insertProjectMembers(ProjectMembers);
		return "addMembers";
	}

	@PostMapping("/addPositions")
	public String addPositionsPost(Positions positions) {
		positionsServiceImpl.insertPosition(positions);
		return "addPositions";
	}

	@PostMapping("/addTeams")
	public String addTeamsPost(Teams teams) {
		teamsServiceImpl.insertTeams(teams);
		return "addTeams";
	}

}
