package com.dagim.auth.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dagim.auth.entity.User;


public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;
	
//	Decortor pattern to pass/map user object
	private User user;
	
	
	public UserPrincipal(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> userRoles = new ArrayList<>();
		this.user.getUserRole().forEach(role ->{
			GrantedAuthority roles = new SimpleGrantedAuthority("ROLE_"+role);
			userRoles.add(roles);
		});
		return userRoles;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.user.getUserPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user.getUserName();
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

		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
