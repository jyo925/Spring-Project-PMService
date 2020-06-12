package com.project.bit.foo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.bit.foo.domain.event.EventGroup;

@Mapper
public interface EventGroupMapper {
	
	String [] selectGroup(String eventId);

	void insertMember(EventGroup eventGroup);
	
	void deleteMember(String userId);
	
}
