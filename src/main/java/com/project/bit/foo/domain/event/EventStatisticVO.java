package com.project.bit.foo.domain.event;

import lombok.Data;

@Data
public class EventStatisticVO {
	
	private String eventType;
	private String eventAmount;

	public EventStatisticVO() {
		
	}

}
