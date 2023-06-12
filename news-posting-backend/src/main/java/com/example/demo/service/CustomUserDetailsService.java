package com.example.demo.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.configuration.JwtRequestFilter;
import com.example.demo.model.JwtRequest;
import com.example.demo.model.JwtResponse;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import com.example.demo.util.JwtUtil;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	
	
	@Autowired
	private UserRepo userRepo;
	
	
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepo.findById(email).get();
		
		if(user != null) {
			return  new org.springframework.security.core.userdetails.User(
				user.getEmail(),
				user.getPassword(),
				getAuthority(user)
			);
		}else {
			throw new UsernameNotFoundException("User with that email '" + email + "' doesn't exist");
		}
	}
	
	private Set getAuthority(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet();
		user.getRole().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
		});
		
		return authorities;
	}
	 
	
	
	 
}
