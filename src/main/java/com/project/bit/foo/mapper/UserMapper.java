package com.project.bit.foo.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.bit.foo.domain.Users;
import com.project.bit.project.domain.PositionDTO;
import com.project.bit.project.domain.TeamDTO;

@Mapper
public interface UserMapper {
	
	@Select("select u.*, p.DUTY_CODE AS duty_code, d.duty_name AS duty_name from USERS u inner join project_members p on p.user_id = u.user_id inner join dutys d on p.duty_code = d.duty_code where u.USER_ID=#{userId} AND rownum <= 1")
	Users selectUserById(String userId);
	
	@Insert("insert into users(USER_ID, USER_PW, USER_NAME, USER_EMAIL, USER_PHONE, POSITION_CODE, TEAM_CODE) "
			       + "values(#{userId}, #{userPw}, #{userName}, #{userEmail}, #{userEmail}, #{positionCode}, #{teamCode})")
	void insertUser(Users user);
	
	@Select("SELECT * FROM USERS WHERE USER_ID=#{userId}")
	Users selectUser(String userId);
	
	@Update("UPDATE USERS SET USER_WEBSITE=#{user.userWebsite}, USER_INFO=#{user.userInfo}, USER_NAME=#{user.userName}, USER_EMAIL=#{user.userEmail}, USER_PHONE=#{user.userPhone} WHERE USER_ID=#{userId}")
	void updateUser(Users user, String userId);
	
	@Update("UPDATE USERS SET USER_PHOTO=#{user.userPhoto} WHERE USER_ID=#{userId}")
	void updateUserPoto(Users user, String userId);
	
	@Update("UPDATE USERS SET USER_PW=#{user.userPw} WHERE USER_ID=#{userId}")
	void updateUserPw(Users user, String userId);
	
	List<Users> selectAll();
	
	List<TeamDTO> selectTeamAll();
	
	List<PositionDTO> selectPositionAll();
	
	List<Users> selectUserByTeam(int teamCode);
	
	List<Users> findUsersByConversationId(String conversationId);

	int count();
	
}
