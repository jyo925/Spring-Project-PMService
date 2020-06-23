package com.project.bit.foo.service.myProjectsViewService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.bit.foo.domain.projectView.MyProjectsVO;
import com.project.bit.foo.mapper.MyProjectsVOMapper;

@Service
public class MyProjectsVOServiceImpl implements MyProjectsVOServise {

	private final MyProjectsVOMapper myProjectsVOMapper;
	
	public MyProjectsVOServiceImpl(MyProjectsVOMapper myProjectsVOMapper) {
		this.myProjectsVOMapper = myProjectsVOMapper;
	}

	@Override
	public List<MyProjectsVO> getProjects(String userId, String today) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yy");
	    LocalDateTime now = LocalDateTime.now();
	    today = dtf.format(now);
		return myProjectsVOMapper.getProjects(userId, today);
	}

}
