package com.project.bit.foo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.bit.foo.domain.event.EventStatisticVO;

@Mapper
public interface EventStatisticMapper {
	
	List<EventStatisticVO> getStatistic();

}
