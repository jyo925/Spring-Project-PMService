package com.project.bit.foo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.bit.foo.domain.Users;
import com.project.bit.foo.mapper.UserMapper;
import com.project.bit.project.mapper.ProjectMemberMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserMapper userMapper;
	@Autowired private ProjectMemberMapper projectMemberMapper;

	public UserServiceImpl() {
		
	}

	@Override
	public Users selectUserById(String USER_ID) {
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

	@Override
	public List<Users> selectUserByTeam(int teamCode) {
		// TODO Auto-generated method stub
		if(teamCode == 0) return projectMemberMapper.selectUserNoMember();
		return userMapper.selectUserByTeam(teamCode);
	}

	@Override
	public Users selectUser(String userId) {
		// TODO Auto-generated method stub
		return userMapper.selectUser(userId);
	}

	@Override
	public void updateUser(Users user, String userId) {
		userMapper.updateUser(user, userId);
		
	}

	@Override
	public void updateUserPoto(Users user, String userId) {
		userMapper.updateUserPoto(user, userId);
		
	}

	@Override
	public void updateUserPw(Users user, String userId) {
		user.setUserPw(bCryptPasswordEncoder.encode(user.getUserPw()));
		userMapper.updateUserPw(user, userId);
		
	}

}
