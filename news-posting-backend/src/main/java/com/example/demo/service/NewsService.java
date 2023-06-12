package com.example.demo.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.News;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repo.NewsRepo;

@Service
public class NewsService {
	
	@Autowired
	private NewsRepo repo;

	//returns true if the user with the same email address already exists in the database or else perform insert operation
		public News addNews(MultipartFile file,News news) throws IOException {
			boolean present = (this.repo.findAll().stream().filter(e -> e.getTitle().equals(news.getTitle())
					&&
					e.getContent().equals(news.getContent())
					&&
					e.getThumnailName().equals(news.getThumnailName())
					).findAny()).isPresent();
			
			if (!present) {
				
				news.setThumbnail(file.getBytes());
				news.setThumnailName(file.getOriginalFilename());
				return this.repo.save(news);
			}
			
			return null;
		}
		
		public List<News> sendNews() {
			
			return this.repo.findAll();
		}
	
		public News aNews(int nId) {
			News news = (this.repo.findAll()).stream().filter(e -> e.getNId() == nId).findAny().get();
			return news;
		}
}
