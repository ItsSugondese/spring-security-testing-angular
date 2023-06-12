package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.example.demo.model.JwtRequest;
import com.example.demo.model.JwtResponse;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import com.example.demo.util.JwtUtil;

@Component
public class JwtService {

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
		String userEmail = jwtRequest.getUserEmail();
		String userPassword = jwtRequest.getUserPassword();
		
		authenticate(userEmail, userPassword);
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
		String newGeneratedToken = jwtUtil.generateToken(userDetails);
		
		User user = userRepo.findById(userEmail).get();
		
		JwtResponse response = new JwtResponse();
		response.setUser(user);
		response.setJwtToken(newGeneratedToken);
		
		return response;
	}

	private void authenticate(String userEmail, String userPassword) throws Exception{
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEmail, userPassword));
		}catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			System.out.println(e);
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
