package com.example.blogapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blogapi.payload.CategoryDto;
import com.example.blogapi.service.CategoryService;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')") //only admin user can access this api
	public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
		CategoryDto savedCategory = categoryService.addCategory(categoryDto);
		return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long categoryId){
		CategoryDto category = categoryService.getCategory(categoryId);
		return ResponseEntity.ok(category);
	}
	
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		List<CategoryDto> categoryList = categoryService.getAllCategories();
		return ResponseEntity.ok(categoryList);
	}
	
	@PutMapping("{id}")
	@PreAuthorize("hasRole('ADMIN')") //only admin user can access this api
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable("id") Long categoryId){
		CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, categoryId);
		return ResponseEntity.ok(updatedCategory);
	}
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('ADMIN')") //only admin user can access this api
	public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId){
		categoryService.deleteCategory(categoryId);
		return ResponseEntity.ok("Category deletd successfully");
	}

}
