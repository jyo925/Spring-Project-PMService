package com.project.bit.project.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.bit.project.domain.ProjectIssueDTO;
import com.project.bit.project.domain.ProjectIssueTypeDTO;

public interface ProjectIssueService {
	List<ProjectIssueDTO> getProjectIssueList(String projectId);
	List<ProjectIssueTypeDTO> getProjectIssueTypeList();
	List<ProjectIssueDTO> getProjectIssueSearch(String issueType, String issueName, String projectCode);
	
	ProjectIssueDTO getProjectIssueOne(String issueId);
	void postProjectIssue(ProjectIssueDTO projectIssue);
	void putProjectIssue(ProjectIssueDTO projectIssueDTO);
	void putProjectFile(MultipartFile issueFile, String issueCode, String issuePath);
	void removeProjectFile(String issueCode, String issuePath);
	void removeProjectIssue(String issueCode);
}

