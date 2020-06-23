package com.project.bit.foo.service.myProjectsViewService;

import java.sql.Date;
import java.util.List;

import com.project.bit.foo.domain.projectView.MyProjectsVO;

public interface MyProjectsVOServise {
	
	List<MyProjectsVO> getProjects(String userId, String today);

}
