package com.dagim.auth.api;


import java.net.URI;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dagim.auth.entity.User;
import com.dagim.auth.repository.UserRepository;


@RestController
@RequestMapping("/auth-service")
public class UserApi {
	
	
	@Autowired
	private UserRepository userRespository;
	
	
	@GetMapping("/")
	public String helloUser(){
		return "<h3> You Have to first send a post request to create users. "
				+ "And then access the rest of the resources by logging in with the created users"
				+ " and authorized roles </h3>";
	}

	@GetMapping("/users/userid/{userId}")
	public User findUserById(@PathVariable String userId) {
		
		return (User)userRespository.findByUserId(Long.parseLong(userId));
	}
	
	@GetMapping("/users")
	public ArrayList<User> getUsers(){
		return (ArrayList<User>)userRespository.findAll();
	}
		
	@GetMapping("/users/role/{userRole}")
	public ArrayList<User> findByUserRole(@PathVariable String userRole){
		
		return (ArrayList<User>)userRespository.findByUserRole(userRole);
	}
	
	@PostMapping("/user")
	public ResponseEntity<ArrayList<User>> createUser(@RequestBody ArrayList<User> users){
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().
				pathSegment("/user").buildAndExpand(users).toUri();
//				.path("/{userId}").buildAndExpand(user.getUserId()).toUri();
		
		users.forEach(user -> {
			userRespository.saveAndFlush(user);			
		});
				
		return ResponseEntity.created(location).build();
	}
	
	
}
