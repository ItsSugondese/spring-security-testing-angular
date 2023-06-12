package com.example.demo.service;

import java.awt.print.Book;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repo.RoleRepo;
import com.example.demo.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo users;
	
	@Autowired
	private RoleRepo roleRepo;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<User> getUsers(){
		return this.users.findAll();
	}
	
	
	
	//returns true if the user with the same email address already exists in the database or else perform insert operation
	public User addUser(User user) {
		boolean present = (this.users.findAll().stream().filter(e -> e.getEmail().equals(user.getEmail())).findAny()).isPresent();
		
		if (!present) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			Role role = roleRepo.findById("User").get();

			Set<Role> roles = new HashSet<>();
			roles.add(role);
			
			user.setRole(roles);
			
			return this.users.save(user);
		}
		
		return null;
	}
	
	public void removeUser(User user) {
		users.delete(user);
	}
	

}
