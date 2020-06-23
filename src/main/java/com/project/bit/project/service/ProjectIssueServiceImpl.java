package com.project.bit.project.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.bit.project.domain.ProjectIssueDTO;
import com.project.bit.project.domain.ProjectIssueTypeDTO;
import com.project.bit.project.mapper.ProjectIssueMapper;

@Service
public class ProjectIssueServiceImpl implements ProjectIssueService {

	@Autowired
	private ProjectIssueMapper projectIssueMapper;
	private static final String uploadDirectory = System.getProperty("user.dir") + "\\uploads";

	@Override
	public List<ProjectIssueDTO> getProjectIssueList(String projectId) {
		return projectIssueMapper.selectProjectIssueList(projectId);
	}

	@Override
	public List<ProjectIssueTypeDTO> getProjectIssueTypeList() {
		return projectIssueMapper.selectProjectIssueTypeList();
	}

	@Override
	public void postProjectIssue(ProjectIssueDTO projectIssue) {
		projectIssueMapper.insertProjectIssue(projectIssue);

	}

	@Override
	public ProjectIssueDTO getProjectIssueOne(String issueId) {
		return projectIssueMapper.selectProjectIssueOne(issueId);
	}

	@Override
	public void putProjectIssue(ProjectIssueDTO projectIssueDTO) {
		projectIssueMapper.updateProjectIssue(projectIssueDTO);
	}

	@Override
	public void putProjectFile(MultipartFile issueFile, String issueCode, String issuePath) {

		if (issueFile != null) {
			deleteFile(issuePath); // 1. 기존 파일삭제
			
			// 2. 새로운 파일 업로드
			// ProjectOutputDTO projectOutputDTO = new ProjectOutputDTO();

			File uploadPath = new File(uploadDirectory, (new SimpleDateFormat("yyyy-MM-dd")).format(new Date()).replace("-", File.separator));

			if (uploadPath.exists() == false) uploadPath.mkdirs();
			
			
			
			try {
				File saveFile = new File(uploadPath, issueFile.getOriginalFilename());
				issueFile.transferTo(saveFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			projectIssueMapper.updateProjectIssueFile(issueCode, uploadPath.getPath() + "\\" + issueFile.getOriginalFilename());
		}

	}
	
	@Override
	public void removeProjectFile(String issueCode, String issuePath) {
		//if(issuePath != null || issuePath != "") deleteFile(issuePath);
		projectIssueMapper.updateProjectIssueFileColumn(issueCode);
		
	}

	@Override
	public void removeProjectIssue(String issueCode) {
		projectIssueMapper.deleteProjectIssue(issueCode);
		
	}
	
	// 파일 삭제
	public void deleteFile(String issuePath) {
		File file = new File(issuePath);
		file.delete();
	}

	@Override
	public List<ProjectIssueDTO> getProjectIssueSearch(String issueType, String issueName, String projectCode) {
		if(issueType.equals("all") && issueName.equals("")) return projectIssueMapper.selectProjectIssueList(projectCode);
		else if(!issueType.equals("all") && issueName.equals("")) return projectIssueMapper.selectProjectIssueListType(issueType, projectCode);
		else if(issueType.equals("all") && !issueName.equals("")) return projectIssueMapper.selectProjectIssueListName(issueName, projectCode);
		else return projectIssueMapper.selectProjectIssueListSearch(issueType, issueName, projectCode);
	}

}
