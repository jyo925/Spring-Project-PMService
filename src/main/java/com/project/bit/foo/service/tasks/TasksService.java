package com.project.bit.foo.service.tasks;

import java.util.List;

import com.project.bit.project.domain.ProjectTaskDTO;

public interface TasksService {
	
	List<ProjectTaskDTO> getTasks();

}
