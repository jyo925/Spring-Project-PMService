package com.project.bit.foo.controller;

import java.security.Principal;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.project.bit.foo.domain.event.Event;
import com.project.bit.foo.service.EventService.EventService;

@RestController
public class EventController {

	@Autowired
	private EventService eventService;

	@GetMapping(value = "/calendar", produces = "application/json; charset=utf8")
	public ModelAndView calendar() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("calendar/calendar");
		return modelAndView;
	}
	
	@PostMapping(value = "/calendar", produces = "application/json; charset=utf8")
	public ModelAndView calendarEdit(Model model, @RequestParam(required = false) String eventId) {
		if (eventId != "") {
			Event event = eventService.selectEvent(eventId);
			model.addAttribute("event", event);
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("calendar/calendar");
		return modelAndView;
	}

	@GetMapping("/calendarE")
	public String calendarE(Model model, Principal principal) {
		JSONArray json = eventService.selectEventById(principal.getName());
		return json.toString();
	}

	public EventController() {

	}

}
