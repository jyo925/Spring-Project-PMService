package com.project.bit.foo.service.projectStatus;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.bit.foo.mapper.ProjectStatusGetMapper;
import com.project.bit.project.domain.ProjectStatusDTO;

@Service
public class projectStatusGetServiceImpl implements ProjectStatusGetServise {

	private final ProjectStatusGetMapper projectStatusGetMapper;
	
	public projectStatusGetServiceImpl(ProjectStatusGetMapper projectStatusGetMapper) {
		this.projectStatusGetMapper = projectStatusGetMapper;
	}

	@Override
	public List<ProjectStatusDTO> selectAllStatusNames() {
		return projectStatusGetMapper.selectAllStatusNames();
	}

}
