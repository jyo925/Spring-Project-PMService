package com.project.bit.foo.service;

import java.util.List;
import java.util.Optional;

import com.project.bit.foo.domain.Users;

public interface UserService {
	
	Optional<Users> selectUserById(String USER_ID);
	
	void insertUser(Users user);
	
	List<Users> selectAll();
	
	List<Users> selectUserByTeam(int teamCode);
	
	Users selectUser(String userId);
	
	void updateUser(Users user, String userId);
	
	void updateUserPoto(Users user, String userId);
	
	void updateUserPw(Users user, String userId);

}
