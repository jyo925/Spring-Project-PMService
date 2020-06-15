package com.project.bit.foo.service.EventService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.bit.foo.domain.event.Event;
import com.project.bit.foo.domain.event.EventGroup;
import com.project.bit.foo.mapper.EventGroupMapper;

@Service
public class EventGroupServiceImpl implements EventGroupService {
	
	private final EventGroupMapper eventGroupMapper;
	private final EventServiceImpl eventService;
	
	public EventGroupServiceImpl(EventGroupMapper eventGroupMapper,
			  					 EventServiceImpl eventService) {
		this.eventGroupMapper = eventGroupMapper;
		this.eventService = eventService;
	}
	

	@Override
	public void insertMember(EventGroup eventGroup, Event event) {
		
		String [] strings = eventGroup.getEventMembers().trim().split(" ");
		for(int i = 0; i<strings.length; i++) {
			eventGroup.setEventId(eventService.getEventId(event));
			eventGroup.setUserId(strings[i]);
			eventGroupMapper.insertMember(eventGroup);
			
		}

	}

	@Override
	public void deleteMember(String userId) {
		eventGroupMapper.deleteMember(userId);
		
	}


	@Override
	public String [] selectGroup(String eventId) {
		return eventGroupMapper.selectGroup(eventId);
	}

	
	
	public String getMembersList(String eventd) {
		
		String [] members = selectGroup(eventd);
		String membersList = "";
		for(int i = 0; i<members.length; i++) {
			membersList +=" "+members[i];
		}
		System.out.println(membersList);
		
		return membersList;
	}
	

}

























