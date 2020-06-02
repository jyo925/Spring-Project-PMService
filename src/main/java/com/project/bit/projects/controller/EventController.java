package com.project.bit.projects.controller;

import java.security.Principal;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.bit.projects.service.EventService.EventService;

@Controller
public class EventController {

	@Autowired
	private EventService eventService;

	@GetMapping("/calendar")
	public String calendar() {
		return "calendar/calendar";
	}

	@GetMapping("/calendarE")
	@ResponseBody
	public String calendarE(Model model, Principal principal) {
		JSONArray json = eventService.selectEventById(principal.getName());

		return json.toString();
	}

	public EventController() {

	}

}
