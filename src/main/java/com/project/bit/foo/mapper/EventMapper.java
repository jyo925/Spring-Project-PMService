package com.project.bit.foo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.project.bit.foo.domain.event.Event;

@Mapper
public interface EventMapper {
	
	//@Select("SELECT * FROM events e INNER JOIN event_groups g ON e.event_id = g.event_id WHERE g.user_id=#{userId}")
	List<Event> selectEventById(String userId);
	
	Event selectEvent(String eventId);

}
