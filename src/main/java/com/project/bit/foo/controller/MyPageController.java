package com.project.bit.foo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.bit.foo.domain.Users;
import com.project.bit.foo.domain.UsersPrincipal;
import com.project.bit.foo.mapper.UserInfoMapper;
import com.project.bit.foo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MyPageController {

	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";

	private final UserService userService;
	private final UserInfoMapper userInfoMapper;

	public MyPageController(UserService userService
						   ,UserInfoMapper userInfoMapper) {
		this.userService = userService;
		this.userInfoMapper = userInfoMapper;
	}

	@GetMapping("/myPage")
	public String myPage(Principal principal, Model model) {
		model.addAttribute("projects", userInfoMapper.getProjects(principal.getName()));
		model.addAttribute("tasks", userInfoMapper.getTasks(principal.getName()));
		model.addAttribute("finished", userInfoMapper.getFinished(principal.getName()));
		model.addAttribute("user", userService.selectUser(principal.getName()));
		System.out.println(userService.selectUser(principal.getName()));
		return "myPage/myPage";
	}

	@PostMapping("/updateUser")
	public String updateUser(Users user, Principal principal) {
		userService.updateUser(user, principal.getName());
		Users principal2 = userService.selectUserById(principal.getName());
		UsersPrincipal usersPrincipal = new UsersPrincipal(principal2);
		Authentication auth = new UsernamePasswordAuthenticationToken(usersPrincipal, usersPrincipal.getUsername(), usersPrincipal.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
		return "redirect:/myPage";
	}

	@PostMapping("/uploadImg")
	public String uploadImg(@RequestParam("files") MultipartFile files, Principal principal, Users user, SecurityContextHolder  session) {
		MultipartFile file = files;
		Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
		log.info(uploadDirectory);
		try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		user.setUserPhoto("/img/"+file.getOriginalFilename());
		userService.updateUserPoto(user, principal.getName());
		Users principal2 = userService.selectUserById(principal.getName());
		UsersPrincipal usersPrincipal = new UsersPrincipal(principal2);
		Authentication auth = new UsernamePasswordAuthenticationToken(usersPrincipal, usersPrincipal.getUsername(), usersPrincipal.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
		return "redirect:/myPage";
		
	}
	
	@PostMapping("/deleteImg")
	public String deleteImg(Model model, Principal principal) {
		String userId = principal.getName();
		Users user = userService.selectUser(userId);
		Path fileNameAndPath = Paths.get(uploadDirectory, user.getUserPhoto().substring(4));
		try {
			Files.deleteIfExists(fileNameAndPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		user.setUserPhoto("");
		userService.updateUserPoto(user, userId);

		return "redirect:/myPage";
		
	}
	
	@PostMapping("/updateUserPw")
	public String updateUserPw(Users user, Principal principal) {
		userService.updateUserPw(user, principal.getName());
		return "redirect:/myPage";
	}

}
