package com.project.bit.projects.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.project.bit.projects.domain.Positions;

@Mapper
public interface PositionMapper {
	
	@Insert("insert into positions(POSITION_CODE, POSITION_NAME) values(#{POSITION_CODE}, #{POSITION_NAME})")
	void insertPosition(Positions positions);

}
