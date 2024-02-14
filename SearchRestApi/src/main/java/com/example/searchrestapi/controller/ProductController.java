package com.example.searchrestapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.searchrestapi.entity.Product;
import com.example.searchrestapi.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/search")
	public ResponseEntity<List<Product>> searchProducts(@RequestParam("query") String query){
		
		return ResponseEntity.ok(productService.searchProduct(query));
	}
	
	@PostMapping("/create")
	public Product createProduct(@RequestBody Product product) {
		return productService.createProduct(product);
	}
}
