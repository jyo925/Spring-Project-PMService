package com.project.bit.foo.service.eventService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.bit.foo.domain.event.EventStatisticVO;
import com.project.bit.foo.mapper.EventStatisticMapper;

@Service
public class EventStatisticServiseImpl implements EventStatisticService {

	private final EventStatisticMapper eventStatisticMapper;
	
	public EventStatisticServiseImpl(EventStatisticMapper eventStatisticMapper) {
		this.eventStatisticMapper = eventStatisticMapper;
	}

	@Override
	public List<EventStatisticVO> getStatistic() {
		return eventStatisticMapper.getStatistic();
	}

}
