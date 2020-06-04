package com.project.bit.projects.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.project.bit.projects.domain.Users;

@Mapper
public interface UserMapper {
	
	@Select("select u.USER_ID, u.USER_PW, u.USER_NAME, u.POSITION_CODE, u.TEAM_CODE, u.USER_EMAIL, u.USER_PHONE, u.USER_REGITDATE, p.DUTY_CODE "
			+ "from USERS u left join project_members p on u.USER_ID = p.USER_ID where u.USER_ID=#{USER_ID}")
	/* @Select("select * from users where user_id=#{USER_ID}") */
	Optional<Users> selectUserById(String USER_ID);
	
	@Insert("insert into users(USER_ID, USER_PW, USER_NAME, USER_EMAIL, USER_PHONE, POSITION_CODE, TEAM_CODE) "
			       + "values(#{USER_ID}, #{USER_PW}, #{USER_NAME}, #{USER_EMAIL}, #{USER_PHONE}, #{POSITION_CODE}, #{TEAM_CODE})")
	void insertUser(Users user);
	
	@Select("select * from users")
	List<Users> selectAll();

}
