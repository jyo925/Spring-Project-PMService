package com.project.bit.foo.service.EventService;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bit.foo.domain.event.Event;
import com.project.bit.foo.mapper.EventMapper;

import lombok.AllArgsConstructor;

@Service
public class EventServiceImpl implements EventService {
	
	
	private final EventMapper eventMapper;
	
	public EventServiceImpl(EventMapper eventMapper) {
		this.eventMapper = eventMapper;
	}

	@Override
	public JSONArray selectEventById(String userId) {
		List<Event> events = eventMapper.selectEventById(userId);
		JSONArray json = new JSONArray();
		for(Event event : events) {
			JSONObject ev = new JSONObject();
			ev.put("eventId", event.getEventId());
			ev.put("title", event.getEventTitle());
			ev.put("start", event.getEventStartDate());
			ev.put("end", event.getEventFinishDate());
			ev.put("description", event.getEventDescription());
			ev.put("allDay", event.isEventAllDay());
			ev.put("place", event.getEventPlace());
			ev.put("type", event.getEventTypeId());
			ev.put("projectCode", event.getProjectCode());
			json.put(ev);
		}
		System.out.println(json);
		return json;
	}

	@Override
	public Event selectEvent(String eventId) {
		return eventMapper.selectEvent(eventId);
	}

}
