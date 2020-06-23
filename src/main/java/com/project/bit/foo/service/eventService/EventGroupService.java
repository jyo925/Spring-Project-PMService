package com.project.bit.foo.service.eventService;

import com.project.bit.foo.domain.event.Event;
import com.project.bit.foo.domain.event.EventGroup;

public interface EventGroupService {
	
	void insertMember(EventGroup eventGroup, Event event);

	void deleteGroup(String eventId);
	
	String [] selectGroup(String eventId);
	
	String getMembersList(String eventId);
}
