package com.project.bit.foo.service.EventService;

import java.util.List;

import com.project.bit.foo.domain.event.Event;
import com.project.bit.foo.domain.event.EventGroup;

public interface EventGroupService {
	
	void insertMember(EventGroup eventGroup, Event event);

	void deleteMember(String userId);
	
	String [] selectGroup(String eventId);
}
