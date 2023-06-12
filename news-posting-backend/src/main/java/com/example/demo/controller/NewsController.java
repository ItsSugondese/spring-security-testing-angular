package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.News;
import com.example.demo.model.User;
import com.example.demo.service.NewsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.annotation.MultipartConfig;

@RestController
@CrossOrigin(value = "*")
public class NewsController {

	@Autowired
	private NewsService newsService;
	
	@PostMapping("/addNews")

	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<News> addUser(@RequestParam("thumbnail") MultipartFile uploadedFile, @RequestParam("news") String stringNews) throws JsonMappingException, JsonProcessingException {
System.out.println(uploadedFile);
		News news = new ObjectMapper().readValue(stringNews, News.class);
		News addedNews = null;
		try {
			
			addedNews= newsService.addNews(uploadedFile, news);
			if (addedNews== null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
			
			return ResponseEntity.of(Optional.of(addedNews));
			
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/getNews")
	public ResponseEntity<List<News>> sendNews() {
		
		return ResponseEntity.of(Optional.of(newsService.sendNews()));
	}
	
	@GetMapping("/getNews/{nId}")
	public ResponseEntity<News> name(@PathVariable("nId") int id) {
		News news = this.newsService.aNews(id);
		if(news == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		System.out.println(news);
		return ResponseEntity.of(Optional.of(news));
	}
}
