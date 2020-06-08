package com.project.bit.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.bit.project.mapper.DashBoardDetailMapper;
import com.project.bit.project.mapper.ProjectMapper;

@SpringBootTest
public class Test {
	@Autowired
	private DashBoardDetailMapper dbMapper;
	
	@Autowired
	private ProjectMapper pjMapper;
	
	@org.junit.jupiter.api.Test
	public void testMapper() {
		dbMapper.selectProjectByName("웹 기반 관리자 페이지 개발");
	};
	
	@org.junit.jupiter.api.Test
	public void testMapper2() {
		dbMapper.selectProjectList();
	}
	
}
