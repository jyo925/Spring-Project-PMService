package com.project.bit.foo.service;

import java.util.List;
import java.util.Optional;

import com.project.bit.foo.domain.Users;


public interface UserService {
	
	Optional<Users> selectUserById(String USER_ID);
	
	void insertUser(Users user);
	
	List<Users> selectAll();

}
