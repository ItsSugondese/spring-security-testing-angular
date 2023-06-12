package com.example.demo.model;

import java.util.Set;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

	@Id
	private String email;
	private String username;
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "USER_ROLE",
			joinColumns = {
					@JoinColumn(name="USER_ID")
			},
			inverseJoinColumns = {
					@JoinColumn(name="ROLE_ID")
			}
			)
	private Set<Role> role;

}
