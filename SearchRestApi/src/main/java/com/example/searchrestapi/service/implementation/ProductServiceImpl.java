package com.example.searchrestapi.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.searchrestapi.entity.Product;
import com.example.searchrestapi.repository.ProductRepository;
import com.example.searchrestapi.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> searchProduct(String query) {
		List<Product> items = productRepository.searchProductsSQL(query);
		return items;
	}

	@Override
	public Product createProduct(Product product) {
		Product savedProduct = productRepository.save(product);
		return savedProduct;
	}

}
