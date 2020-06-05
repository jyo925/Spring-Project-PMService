package com.project.bit.foo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bit.foo.domain.Users;
import com.project.bit.foo.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	public UserServiceImpl() {
		
	}

	@Override
	public Optional<Users> selectUserById(String USER_ID) {
		return userMapper.selectUserById(USER_ID);
	}

	@Override
	public void insertUser(Users user) {
		userMapper.insertUser(user);
		
	}

	@Override
	public List<Users> selectAll() {
		return userMapper.selectAll();
	}

}
