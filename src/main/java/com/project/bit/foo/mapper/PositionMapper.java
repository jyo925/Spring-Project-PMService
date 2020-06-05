package com.project.bit.foo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.project.bit.foo.domain.Positions;

@Mapper
public interface PositionMapper {
	
	@Insert("insert into positions(POSITION_CODE, POSITION_NAME) values(#{POSITION_CODE}, #{POSITION_NAME})")
	void insertPosition(Positions positions);

}
