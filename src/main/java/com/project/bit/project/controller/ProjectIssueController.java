package com.project.bit.project.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.project.bit.foo.domain.Users;
import com.project.bit.foo.service.UserService;
import com.project.bit.project.domain.ProjectCriteria;
import com.project.bit.project.domain.ProjectIssueDTO;
import com.project.bit.project.domain.ProjectIssueTypeDTO;
import com.project.bit.project.service.ProjectIssueService;
import com.project.bit.project.service.ProjectService;

@Controller
public class ProjectIssueController {
	
	@Autowired private ProjectIssueService projectIssueService;
	@Autowired private UserService userService;
	@Autowired private ProjectService projectService;
	
	@GetMapping("/goProjectIssueAdd")
	public String goProjectIssueAdd(@ModelAttribute ProjectIssueDTO projectIssueDTO, Model model, ProjectCriteria cri) {
		model.addAttribute("projectList", projectService.getProjectAll());
		return "/project/projectIssueInsert";
	}
	
	@GetMapping("/projectIssueDetail/{issueId}")
	public String goProjectIssueDetail(@PathVariable String issueId, Model model, ProjectCriteria cri) {
		model.addAttribute("issue", projectIssueService.getProjectIssueOne(issueId));
		model.addAttribute("projectList", projectService.getProjectListAll(cri));
		return "/project/projectIssueDetail";
	}
	
	@PostMapping("/postProjectIssue")
	public String postProjectIssue(MultipartFile issue_file, ProjectIssueDTO projectIssue, Principal principal) {
		//ProjectOutputDTO projectOutputDTO = new ProjectOutputDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String uploadFolder = "C:\\upload";
		String uploadFolderPath = sdf.format(new Date()).replace("-", File.separator);

		File uploadPath = new File(uploadFolder, uploadFolderPath);

		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		
		projectIssue.setIssueUserId(principal.getName());
		projectIssue.setIssueFileName(issue_file.getOriginalFilename());
		projectIssue.setIssueFilePath(uploadPath.getPath() + "\\" + issue_file.getOriginalFilename());
		System.err.println(projectIssue);
		
		/*
		 * projectOutputDTO.setOutputName(issue_file.getOriginalFilename());
		 * projectOutputDTO.setOutputUser(principal.getName());
		 * projectOutputDTO.setOutputPath(uploadPath.getPath() + "\\" +
		 * issue_file.getOriginalFilename());
		 * projectOutputDTO.setOutputTypeCode("ISSUE");
		 * projectOutputDTO.setTaskCode(projectIssue.getTaskCode());
		 */
		
		try {
			File saveFile = new File(uploadPath, projectIssue.getIssueFileName());
			issue_file.transferTo(saveFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		projectIssueService.postProjectIssue(projectIssue);
		//projectOutputService.postProjectOutput(projectOutputDTO);
		
		return "redirect:/projectList";
	}
	
	@GetMapping("/removeProjectIssue/{issueCode}")
	public String removeProjectIssue(@PathVariable String issueCode) {
		projectIssueService.removeProjectIssue(issueCode);
		return "redirect:/projectList";
	}
	
	
	
	
	
	@ModelAttribute("userList")
	public List<Users> getUserList(){
		return userService.selectAll();
	}
	
	@ModelAttribute("issueTypeList")
	public List<ProjectIssueTypeDTO> getProjectIssueTypeList(){
		return projectIssueService.getProjectIssueTypeList();
	}
}
