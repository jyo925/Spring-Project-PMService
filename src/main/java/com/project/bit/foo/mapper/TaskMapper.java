package com.project.bit.foo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.project.bit.project.domain.ProjectTaskDTO;

@Mapper
public interface TaskMapper {
	
	@Select("SELECT TASK_CODE, TASK_NAME FROM PROJECT_TASKS")
	List<ProjectTaskDTO> selectTasks();

}
