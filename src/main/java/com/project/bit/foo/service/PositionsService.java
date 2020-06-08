package com.project.bit.foo.service;

import java.util.List;

import com.project.bit.foo.domain.Positions;
import com.project.bit.project.domain.PositionDTO;

public interface PositionsService {
	
	void insertPosition(Positions positions);
	
	List<PositionDTO> getPositionAll();

}
