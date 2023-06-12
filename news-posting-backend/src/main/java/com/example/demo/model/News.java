package com.example.demo.model;

import org.springframework.beans.factory.annotation.Value;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class News {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int nId;
	private String title;
	private String content;
	private String thumnailName;
	private byte[] thumbnail;
	
	@Value("0")
	private int views;
	@Value("0")
	private int likes;
	
}
