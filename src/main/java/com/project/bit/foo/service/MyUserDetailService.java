package com.project.bit.foo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bit.foo.domain.Users;
import com.project.bit.foo.domain.UsersPrincipal;
import com.project.bit.foo.mapper.UserMapper;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private UserMapper userMapper;

	public MyUserDetailService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

		Users user = userMapper.selectUserById(userId);
		UsersPrincipal usersPrincipal = new UsersPrincipal(user);
		return usersPrincipal;

	}

	@Transactional
	public void joinUser(Users users) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		users.setUserPw(passwordEncoder.encode(users.getUserPw()));

	}

}