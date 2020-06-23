package com.project.bit.foo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.bit.foo.domain.Users;
import com.project.bit.foo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MyPageController {

	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";

	private final UserService userService;

	public MyPageController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/myPage")
	public String myPage(Principal principal, Model model) {
		model.addAttribute("user", userService.selectUser(principal.getName()));
		return "myPage/myPage";
	}

	@PostMapping("/updateUser")
	public String updateUser(Users user, Principal principal) {
		userService.updateUser(user, principal.getName());
		return "redirect:/myPage";
	}

	@PostMapping("/uploadImg")
	public String uploadImg(Model model, @RequestParam("files") MultipartFile files, Principal principal, Users user) {
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
