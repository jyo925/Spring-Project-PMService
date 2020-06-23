package com.project.bit.foo.service.eventService;

import java.util.List;

import com.project.bit.foo.domain.event.EventStatisticVO;

public interface EventStatisticService {
	
	List<EventStatisticVO> getStatistic();

}
