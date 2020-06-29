package com.project.bit.foo.service.tasks;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.bit.foo.mapper.TaskMapper;
import com.project.bit.project.domain.ProjectTaskDTO;

@Service
public class TasksServiceImpl implements TasksService {

	private final TaskMapper taskMapper;
	
	public TasksServiceImpl(TaskMapper taskMapper) {
		this.taskMapper = taskMapper;
	}

	@Override
	public List<ProjectTaskDTO> getTasks() {
		return taskMapper.selectTasks();
	}

}
