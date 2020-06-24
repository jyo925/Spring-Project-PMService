package com.project.bit.foo.service.projectStatus;

import java.util.List;

import com.project.bit.project.domain.ProjectStatusDTO;

public interface ProjectStatusGetServise {
	
	List<ProjectStatusDTO> selectAllStatusNames();

}
