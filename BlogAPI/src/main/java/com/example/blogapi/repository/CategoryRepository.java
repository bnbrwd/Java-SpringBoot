package com.example.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blogapi.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
