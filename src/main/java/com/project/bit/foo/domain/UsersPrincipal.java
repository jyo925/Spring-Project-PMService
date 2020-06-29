package com.project.bit.foo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class UsersPrincipal implements UserDetails{

	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1596108203388622014L;
	private Users users;
	
	public UsersPrincipal(Users users) {
		this.users = users;
	}
	
	public UsersPrincipal() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authority = new ArrayList<GrantedAuthority>();
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(this.users.getDutyCode());
		authority.add(grantedAuthority);
		
		return authority;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.users.getUserPw();
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.users.getUserId();
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
	
}