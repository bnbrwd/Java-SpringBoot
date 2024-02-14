package com.example.springdatajpa.service;

import java.util.List;

import com.example.springdatajpa.dto.ProductDto;

public interface ProductService {
	
	String saveProduct(ProductDto productDto);
	String updateProduct(Long id,ProductDto productDto);
	ProductDto getProductById(Long id);
	List<ProductDto> getAllProduct();
	String deleteProductById(Long id);
}
