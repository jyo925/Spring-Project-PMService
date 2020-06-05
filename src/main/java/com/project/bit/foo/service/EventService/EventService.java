package com.project.bit.foo.service.EventService;

import org.json.JSONArray;

import com.project.bit.foo.domain.event.Event;

public interface EventService {
	
	JSONArray selectEventById(String userId);
	
	Event selectEvent(String eventId);

}
