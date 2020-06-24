package com.project.bit.foo.service.eventService;

import java.util.List;

import com.project.bit.foo.domain.event.EventTypes;

public interface EventTypesService {
	
	List<EventTypes> selectAllTypes();

}
