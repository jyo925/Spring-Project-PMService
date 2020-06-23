package com.project.bit.foo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FilesIOController {

	public FilesIOController() {
		// TODO Auto-generated constructor stub
	}
	
	
	@GetMapping("/files")
	public String filesIO() {
		return "filesIO/filesIO";
	}

}
