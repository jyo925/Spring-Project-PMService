package com.project.bit.projects.service.EventService;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bit.projects.domain.event.Event;
import com.project.bit.projects.mapper.EventMapper;

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
			ev.put("title", event.getEventTitle());
			ev.put("start", event.getEventStartDate());
			ev.put("end", event.getEventFinishDate());
			json.put(ev);
		}
		return json;
	}

}
