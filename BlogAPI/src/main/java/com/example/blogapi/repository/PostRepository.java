package com.example.blogapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blogapi.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
	
	List<Post> findByCategoryId(Long categoryId);
}
