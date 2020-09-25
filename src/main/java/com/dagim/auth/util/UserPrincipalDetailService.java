package com.dagim.auth.util;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dagim.auth.entity.User;
import com.dagim.auth.repository.UserRepository;


@Service
public class UserPrincipalDetailService implements UserDetailsService {
	
	private UserRepository userRepository;

	
	public UserPrincipalDetailService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		User user = userRepository.findByUserName(userName);
		UserPrincipal userPrincipal = new UserPrincipal(user);
		
		return userPrincipal;
	}

}
