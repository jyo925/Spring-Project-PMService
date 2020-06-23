package com.project.bit.foo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.bit.foo.domain.projectView.MyProjectsVO;

@Mapper
public interface MyProjectsVOMapper {
	
	List<MyProjectsVO> getProjects(String userId, String today);

}
