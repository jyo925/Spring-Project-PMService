package com.project.bit.foo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.bit.foo.domain.Project;
import com.project.bit.foo.domain.Users;
import com.project.bit.foo.domain.event.Event;
import com.project.bit.foo.domain.event.EventGroup;
import com.project.bit.foo.domain.event.EventTypes;
import com.project.bit.foo.service.ProjectService2;
import com.project.bit.foo.service.UserService;
import com.project.bit.foo.service.EventService.EventGroupService;
import com.project.bit.foo.service.EventService.EventService;
import com.project.bit.foo.service.EventService.EventTypesService;

@Controller
public class EventController {

	private final EventTypesService eventTypesService;
	private final EventService eventService;
	private final ProjectService2 projectService;
	private final UserService userService;
	private final EventGroupService eventGroupService;

	public EventController(EventService eventService, 
			               EventTypesService eventTypesService,
			               ProjectService2 projectService,
			               UserService userService,
			               EventGroupService eventGroupService) {
		this.eventService = eventService;
		this.eventTypesService = eventTypesService;
		this.projectService = projectService;
		this.userService = userService;
		this.eventGroupService = eventGroupService;
	}

	@GetMapping("/calendar")
	public String calendar(Model model) {
		List<EventTypes> eventTypes = eventTypesService.selectAllTypes();
		List<Project> projectList = projectService.selectAllProjects();
		List<Users> usersList = userService.selectAll();
		model.addAttribute("projectList", projectList);
		model.addAttribute("eventTypes", eventTypes);
		model.addAttribute("usersList", usersList);
		return "calendar/calendar";
	}

	/*
	 * @PostMapping(value = "/calendarPost", produces =
	 * "application/json; charset=utf8") public String calendarPost(Model
	 * model, @RequestParam(defaultValue = "") String eventId) {
	 * 
	 * System.out.println(eventId);
	 * 
	 * if (eventId != "") { Event event = eventService.selectEvent(eventId);
	 * model.addAttribute("event", event); System.out.println(event); } return
	 * "calendar/calendar"; }
	 */

	@PostMapping("/addEvent")
	public String calendarPost(Event event, EventGroup eventGroup) {
		eventService.insertEvent(event);
		eventGroupService.insertMember(eventGroup, event);
		return "redirect:/calendar";
	}

}
