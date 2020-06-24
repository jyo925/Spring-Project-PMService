package com.project.bit.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.bit.project.domain.ProjectIssueDTO;
import com.project.bit.project.domain.ProjectIssueTypeDTO;

@Mapper
public interface ProjectIssueMapper {
	List<ProjectIssueDTO> selectProjectIssueList(String projectId);
	List<ProjectIssueDTO> selectProjectIssueListType(@Param("issueType") String issueType,@Param("projectCode") String projectCode);
	List<ProjectIssueDTO> selectProjectIssueListName(@Param("issueName") String issueName,@Param("projectCode") String projectCode);
	List<ProjectIssueDTO> selectProjectIssueListSearch(@Param("issueType") String issueType, @Param("issueName") String issueName, @Param("projectCode") String projectCode);
	
	List<ProjectIssueTypeDTO> selectProjectIssueTypeList();
	ProjectIssueDTO selectProjectIssueOne(String issueId);
	
	void insertProjectIssue(ProjectIssueDTO projectIssue);
	void updateProjectIssue(ProjectIssueDTO projectIssueDTO);
	void updateProjectIssueFile(@Param("issueCode")String issueCode, @Param("issuePath")String issuePath);
	void updateProjectIssueFileColumn(String issueCode);
	void deleteProjectIssue(String issueCode);
}
