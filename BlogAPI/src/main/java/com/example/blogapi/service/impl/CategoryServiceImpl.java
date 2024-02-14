package com.example.blogapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blogapi.entity.Category;
import com.example.blogapi.exception.ResourceNotFoundException;
import com.example.blogapi.payload.CategoryDto;
import com.example.blogapi.repository.CategoryRepository;
import com.example.blogapi.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		Category saveCategory = categoryRepository.save(category);
		return modelMapper.map(saveCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategory(Long CategoryId) {
		Category category = categoryRepository.findById(CategoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", CategoryId));
		return modelMapper.map(category, CategoryDto.class);
		
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		return categories.stream().map((category) -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		category.setName(categoryDto.getName());
		category.setDescription(categoryDto.getDescription());
		//category.setId(categoryDto.getId());
		
		Category updatedCategory = categoryRepository.save(category);
		return modelMapper.map(updatedCategory, CategoryDto.class);
		
	}

	@Override
	public void deleteCategory(Long categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		
		categoryRepository.delete(category);
		
	}

}
