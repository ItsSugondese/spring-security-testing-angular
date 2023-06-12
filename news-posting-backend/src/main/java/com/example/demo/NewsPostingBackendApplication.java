package com.example.demo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repo.RoleRepo;
import com.example.demo.repo.UserRepo;

@SpringBootApplication
public class NewsPostingBackendApplication implements CommandLineRunner {

	@Autowired
	private UserRepo users;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Value("${app.adminEmail}")
	private String email;
	
	@Value("${app.adminPassword}")
	private String password;
	
	public static void main(String[] args) {
		SpringApplication.run(NewsPostingBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		User user = new User();
		user.setEmail(email);
		user.setPassword(encoder.encode(password));
		user.setUsername("Admin");
		
		Set<Role> roles = new HashSet<>();
		
		Role role = new Role();
		role.setRole("Admin");
		role.setRole("Admin");
		roleRepo.save(role);
		
		roles.add(role);
		
		user.setRole(roles);
		// TODO Auto-generated method stub
		users.save(user);
		
	}

}
