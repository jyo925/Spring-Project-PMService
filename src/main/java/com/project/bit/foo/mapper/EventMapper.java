package com.project.bit.foo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.bit.foo.domain.event.Event;

@Mapper
public interface EventMapper {
	
	List<Event> selectEventById(String userId);
	
	Event selectEvent(String eventId);
	
	void insertEvent(Event event);
	
	void deleteEvent(String eventId);

}
