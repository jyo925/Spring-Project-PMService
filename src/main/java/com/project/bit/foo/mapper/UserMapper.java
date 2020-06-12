package com.project.bit.foo.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.project.bit.foo.domain.Users;

@Mapper
public interface UserMapper {
	
	@Select("select u.USER_ID, u.USER_PW, u.USER_NAME, u.POSITION_CODE, u.TEAM_CODE, u.USER_EMAIL, u.USER_PHONE, u.USER_REGITDATE, p.DUTY_CODE "
			+ "from USERS u left join project_members p on u.USER_ID = p.USER_ID where u.USER_ID=#{userId}")
	Optional<Users> selectUserById(String userId);
	
	@Insert("insert into users(USER_ID, USER_PW, USER_NAME, USER_EMAIL, USER_PHONE, POSITION_CODE, TEAM_CODE) "
			       + "values(#{userId}, #{userPw}, #{userName}, #{userEmail}, #{userPhone}, #{positionCode}, #{teamCode})")
	void insertUser(Users user);
	
	@Select("select * from users")
	List<Users> selectAll();

}
