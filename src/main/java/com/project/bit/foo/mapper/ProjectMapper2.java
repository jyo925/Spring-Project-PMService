package com.project.bit.foo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.project.bit.foo.domain.Project;

@Mapper
public interface ProjectMapper2 {
	
	@Select("SELECT * FROM PROJECTS")
	List<Project> selectAllProjects();

}
