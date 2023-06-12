package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class JwtRequest {

	private String userEmail;
	private String userPassword;
}
