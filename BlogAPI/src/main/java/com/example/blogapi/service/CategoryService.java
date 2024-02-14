package com.example.blogapi.service;

import java.util.List;

import com.example.blogapi.payload.CategoryDto;

public interface CategoryService {
	
	CategoryDto addCategory(CategoryDto categoryDto);
	
	CategoryDto getCategory(Long CategoryId);
	
	List<CategoryDto> getAllCategories();
	
	CategoryDto updateCategory(CategoryDto categoryDto,Long categoryId);
	
	void deleteCategory(Long categoryId);
}
