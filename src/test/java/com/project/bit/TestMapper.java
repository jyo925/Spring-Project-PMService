package com.project.bit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.bit.foo.mapper.EventMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class TestMapper {

	public TestMapper() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	EventMapper eventMapper;
	
	@Test
	public void test() {
		log.info(eventMapper.selectEventById("333").toString());
		
	}

}
