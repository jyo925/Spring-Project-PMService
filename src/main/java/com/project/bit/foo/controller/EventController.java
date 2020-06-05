package com.project.bit.foo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.bit.foo.domain.event.Event;
import com.project.bit.foo.domain.event.EventTypes;
import com.project.bit.foo.service.EventService.EventService;
import com.project.bit.foo.service.EventService.EventTypesService;

@Controller
public class EventController {

	private final EventTypesService eventTypesService;
	private final EventService eventService;
	
	public EventController(EventService eventService,
			               EventTypesService eventTypesService) {
		this.eventService = eventService;
		this.eventTypesService = eventTypesService;
	}
	
	@GetMapping("/calendar")
	public String calendar(Model model) {
		List<EventTypes> eventTypes = eventTypesService.selectAllTypes();
		model.addAttribute("eventTypes", eventTypes);
		return "calendar/calendar";
	}

	@PostMapping(value = "/calendarPost", produces = "application/json; charset=utf8")
	public String calendarPost(Model model, @RequestParam(defaultValue = "") String eventId) {

		System.out.println(eventId);

		if (eventId != "") {
			Event event = eventService.selectEvent(eventId);
			model.addAttribute("event", event);
			System.out.println(event);
		}
		return "calendar/calendar";
	}

	

}
