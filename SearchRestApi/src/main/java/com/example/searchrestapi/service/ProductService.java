package com.example.searchrestapi.service;

import java.util.List;

import com.example.searchrestapi.entity.Product;

public interface ProductService {
	
	List<Product> searchProduct(String query);

	Product createProduct(Product product);
	
}
