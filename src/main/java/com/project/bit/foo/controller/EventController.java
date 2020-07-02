package com.project.bit.foo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.bit.foo.domain.Project;
import com.project.bit.foo.domain.Users;
import com.project.bit.foo.domain.event.Event;
import com.project.bit.foo.domain.event.EventGroup;
import com.project.bit.foo.domain.event.EventStatisticVO;
import com.project.bit.foo.domain.event.EventTypes;
import com.project.bit.foo.service.ProjectService2;
import com.project.bit.foo.service.UserService;
import com.project.bit.foo.service.EventService.EventGroupService;
import com.project.bit.foo.service.EventService.EventService;
import com.project.bit.foo.service.EventService.EventStatisticService;
import com.project.bit.foo.service.EventService.EventTypesService;

@Controller
public class EventController {

	private final EventTypesService eventTypesService;
	private final EventService eventService;
	private final ProjectService2 projectService;
	private final UserService userService;
	private final EventGroupService eventGroupService;
	private final EventStatisticService eventStatisticService;

	public EventController(EventService eventService, EventTypesService eventTypesService,
			ProjectService2 projectService, UserService userService, EventGroupService eventGroupService,EventStatisticService eventStatisticService) {
		this.eventService = eventService;
		this.eventTypesService = eventTypesService;
		this.projectService = projectService;
		this.userService = userService;
		this.eventGroupService = eventGroupService;
		this.eventStatisticService = eventStatisticService;
	}

	@GetMapping("/calendar")
	public String calendar(Model model) {
		List<EventTypes> eventTypes = eventTypesService.selectAllTypes();
		List<Project> projectList = projectService.selectAllProjects();
		List<Users> usersList = userService.selectAll();
		List<EventStatisticVO> statistic = eventStatisticService.getStatistic();
		model.addAttribute("projectList", projectList);
		model.addAttribute("eventTypes", eventTypes);
		model.addAttribute("usersList", usersList);
		model.addAttribute("statistic", statistic);
		return "calendar/calendar";
	}

	@PostMapping("/calendarPost")
	public ResponseEntity<String> calendarPost(@RequestParam("eventId") String eventId) {
		return new ResponseEntity<String>(eventGroupService.getMembersList(eventId), HttpStatus.OK);
	}

	@PostMapping("/addEvent")
	public String calendarPost(Event event, EventGroup eventGroup) {
		eventService.insertEvent(event);
		eventGroupService.insertMember(eventGroup, event);
		return "redirect:/calendar";
	}

	@PostMapping("/editEvent")
	public String calendarEditEvent(@RequestParam String eventId, Event event, EventGroup eventGroup) {
		eventGroupService.deleteGroup(eventId);
		eventService.deleteEvent(eventId);
		eventService.insertEvent(event);
		eventGroupService.insertMember(eventGroup, event);

		return "redirect:/calendar";
	}
	
	@PostMapping("/deleteEvent")
	public String calendarDeleteEvent(@RequestParam String eventId) {
		eventGroupService.deleteGroup(eventId);
		eventService.deleteEvent(eventId);

		return "redirect:/calendar";
	}

}
