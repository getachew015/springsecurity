package com.dagim.auth.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dagim.auth.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	ArrayList<User> findAll();
	User findByUserId(Long userId);
	ArrayList<User> findByUserRole(String role);
	User findByUserName(String name);

}
