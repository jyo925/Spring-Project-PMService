package com.project.bit.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.bit.approval.domain.Criteria;
import com.project.bit.project.domain.ProjectCriteria;
import com.project.bit.project.domain.ProjectDTO;
import com.project.bit.project.domain.ProjectPage;
import com.project.bit.project.domain.ProjectStatusDTO;
import com.project.bit.project.domain.ProjectTypeDTO;
import com.project.bit.project.service.ProjectService;
import com.project.bit.project.service.ProjectTaskService;

@Controller
public class ProjectController {

	@Autowired private ProjectService projectService;
	@Autowired private ProjectTaskService projectTaskService;
	
	// main으로 이동
	@GetMapping("/index")
	public String goIndex() {
		return "index";
	}
	
	// 프로젝트 리스트
	@GetMapping("/projectList")
	public String getProjectList(ProjectCriteria cri, Model model) {
		model.addAttribute("projectList", projectService.getProjectListAll());
		model.addAttribute("pageMaker", new ProjectPage(cri,projectService.getProjectListAllAccount()));
		model.addAttribute("typCode", "all");
		return "/project/getProjectList";
	}
	
	@GetMapping("/project/type/search/{typeCode}")
	public String getProjectListByType(@PathVariable("typeCode") String typeCode,
			ProjectCriteria cri, Model model) {
		model.addAttribute("projectList", projectService.getProjectListByType(cri, typeCode));
		model.addAttribute("pageMaker", new ProjectPage(cri, projectService.getProjectListAccount(typeCode)));
		model.addAttribute("typCode", typeCode);
		
		if(typeCode.equals("all")) return "redirect:/projectList";
		return "/project/getProjectList";
	}
	
	@GetMapping("/project/name/search/{typeCode}/{name}")
	public String getProjectListSearch(@PathVariable("typeCode") String typeCode, @PathVariable("name") String name,
			ProjectCriteria cri, Model model) {
		if(typeCode.equals("all") && typeCode.equals("")) return "redirect:/projectList";
		
		model.addAttribute("projectList", projectService.getProjectSearch(cri, typeCode, name));
		model.addAttribute("pageMaker", new ProjectPage(cri, projectService.getPageTotal(typeCode, name)));
		model.addAttribute("typCode", typeCode);
		
		return "/project/getProjectList";
	}
	
	// 프로젝트 등록
	@PostMapping("/projectInsert")
	public String postProject(ProjectDTO projectDTO) {
		projectService.postProject(projectDTO);
		return "redirect:/projectList";
	}		
	
	// 프로젝트 유형 리스트
	@ModelAttribute("projectTypeList")
	public List<ProjectTypeDTO> getProjectType(){
		return projectService.getProjectTypeAll();
	}
	
	// 프로젝트 상태 리스트
	@ModelAttribute("projectStatus")
	public List<ProjectStatusDTO> getProjectStatus(){
		return projectService.getProjectStatusListAll();
	}

}
