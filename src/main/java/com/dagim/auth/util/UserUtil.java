package com.dagim.auth.util;

import java.util.ArrayList;

import com.dagim.auth.entity.User;

public class UserUtil {
	
	private ArrayList<User> users;
	private User user ;

	
	public UserUtil(ArrayList<User> users) {

		this.users = users;
	}

	public ArrayList<User> getUsers() {		
		return users;
	}
		
	public void setUsers(ArrayList<User> users) {		
		this.users = users;
	}

	public User getUserById(String id) {
		users.forEach(user -> {
			if(user.getUserId().equals(id)) 
				this.user = user;
		});
		
		return this.user;
	}
	
	public ArrayList<User> getUserByRole(String role){
		ArrayList <User> usersByRole = new ArrayList<User>();
		
		users.forEach(user -> {
			if(user.getUserRole().equals(role))
				usersByRole.add(user);
		});
		
		return usersByRole;
	}
	

}
