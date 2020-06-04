package com.project.bit.projects.service;

import java.util.List;
import java.util.Optional;

import com.project.bit.projects.domain.Users;


public interface UserService {
	
	Optional<Users> selectUserById(String USER_ID);
	
	void insertUser(Users user);
	
	List<Users> selectAll();

}
