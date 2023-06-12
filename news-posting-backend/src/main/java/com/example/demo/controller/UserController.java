package com.example.demo.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repo.RoleRepo;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(value = "*")
public class UserController {

	@Autowired
	private UserService userService;
	


	
	@Value("${app.adminEmail}")
	String mail;

	@GetMapping("/getUsers")
	public ResponseEntity<List<User>> getUsers() {
		List<User> users = this.userService.getUsers();
		if (users.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(users));
	}

	@PostMapping("/addUser")
	public ResponseEntity<User> addUser(@RequestBody User user) {

		User addedUser= null;
		try {
			
			addedUser = userService.addUser(user);
			if (addedUser == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
			return ResponseEntity.of(Optional.of(addedUser));
			
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/forUser")
	@PreAuthorize("hasRole('User')")
	public String checkUser(Principal principal) {
		System.out.println(principal.getName());
		return "Fucking bitch user";
	}
	
	@GetMapping("/forAdmin")
	@PreAuthorize("hasRole('Admin')")
	public String checkAdmin(Principal principal) {
		System.out.println(principal.getName());
		return "Fucking Admin fucker admin";
	}
	
	@GetMapping("/checking")
	public boolean checkToken() {
		return true;
	}
	
	

}
