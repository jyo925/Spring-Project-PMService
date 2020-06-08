package com.project.bit.foo.domain.event;

import java.sql.Date;

import lombok.Data;

@Data
public class Event {
	
	private String eventId;
	private String eventTitle;
	private Date eventStartDate;
	private Date eventFinishDate;
	private String eventDescription;
	private boolean eventAllDay;
	private String eventPlace;
	private String eventTypeId;
	private String eventCode;
	private String projectCode;

	public Event() {
		// TODO Auto-generated constructor stub
	}

}
