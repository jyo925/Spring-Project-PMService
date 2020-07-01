package com.project.bit.foo.service.AuthorityService;

import org.springframework.stereotype.Service;

import com.project.bit.foo.mapper.AuthorityMapper;

@Service
public class AuthorityServiseImpl implements AuthorityService {

	private final AuthorityMapper authorityMapper;

	public AuthorityServiseImpl(AuthorityMapper authorityMapper) {
		this.authorityMapper = authorityMapper;
	}

	@Override
	public void editMembers(String data) {

		String[] members = data.trim().split(" ");
		for (int i = 0; i < members.length; i++) {
			String[] member = members[i].split(",");
			authorityMapper.updateDuty(member[0], member[1]);
		}
	}

}
