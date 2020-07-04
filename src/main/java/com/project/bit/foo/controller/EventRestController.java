
package com.project.bit.foo.controller;

import java.security.Principal;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bit.foo.service.eventService.EventGroupService;
import com.project.bit.foo.service.eventService.EventService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class EventRestController {
	
	@Autowired
	private EventGroupService eventGroupService;
	
	@Autowired
	private EventService eventService;
	
	public EventRestController() {
		
	}
	
	@GetMapping("/calendarE")
	public String calendarE(Model model, Principal principal) {
		JSONArray json = eventService.selectEventById(principal.getName());
		System.out.println("here"+json);


		return json.toString();
	}
	

}
