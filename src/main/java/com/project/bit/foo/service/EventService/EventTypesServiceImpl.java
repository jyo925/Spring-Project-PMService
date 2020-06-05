package com.project.bit.foo.service.EventService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.bit.foo.domain.event.EventTypes;
import com.project.bit.foo.mapper.EventTypesMapper;

@Service
public class EventTypesServiceImpl implements EventTypesService {
	
	private final EventTypesMapper eventTypesMapper;
	
	public EventTypesServiceImpl(EventTypesMapper eventTypesMapper) {
		this.eventTypesMapper = eventTypesMapper;
	}

	@Override
	public List<EventTypes> selectAllTypes() {
		return eventTypesMapper.selectAllTypes();
	}

}
