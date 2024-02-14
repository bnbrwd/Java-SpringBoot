package com.example.springdatajpa.service.implementation;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springdatajpa.dto.ProductDto;
import com.example.springdatajpa.entity.Product;
import com.example.springdatajpa.repository.ProductRepository;
import com.example.springdatajpa.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public String saveProduct(ProductDto productDto) {
		
		Product product = new Product();
		product.setName("Product 1");
		product.setDescription("Product 1 description");
		product.setSku("10ABC");
		product.setPrice(new BigDecimal(100));
		product.setActive(true);
		product.setImageUrl("image.png");
		
		Product savedProduct = productRepository.save(product);
		return "Product saved successfully : "+savedProduct.getName();
	}

	@Override
	public String updateProduct(Long id, ProductDto productDto) {
		
		Product product = productRepository.findById(id).get();
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setSku(productDto.getSku());
		product.setPrice(productDto.getPrice());
		product.setActive(productDto.isActive());
		product.setImageUrl(productDto.getImageUrl());
		Product savedProduct = productRepository.save(product);
		return "Product updated successfully : "+savedProduct.getName();
	}

	@Override
	public ProductDto getProductById(Long id) {
		Product product = productRepository.findById(id).get();
		ProductDto productDto = new ProductDto();
		productDto.setActive(product.isActive());
		productDto.setDescription(product.getDescription());
		productDto.setImageUrl(product.getImageUrl());
		productDto.setName(product.getName());
		productDto.setPrice(product.getPrice());
		productDto.setSku(product.getSku());
		return productDto;
	}

	@Override
	public List<ProductDto> getAllProduct() {
		List<Product> item = productRepository.findAll();
		return item.stream().map(product -> mapToDto(product)).collect(Collectors.toList());	
	}
	
	//convert Entity into Dto
		private ProductDto mapToDto(Product product) {
			ProductDto productDto = new ProductDto();
			productDto.setActive(product.isActive());
			productDto.setDescription(product.getDescription());
			productDto.setImageUrl(product.getImageUrl());
			productDto.setName(product.getName());
			productDto.setPrice(product.getPrice());
			productDto.setSku(product.getSku());
			return productDto;
		}

		@Override
		public String deleteProductById(Long id) {
			productRepository.deleteById(id);
			return "Deleted successfully";
		}

}
