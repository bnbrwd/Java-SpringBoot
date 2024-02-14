package com.example.searchrestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.searchrestapi.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	//Product is entity class name  this is a jpl query
	@Query("SELECT p FROM Product p WHERE" + 
			"p.name LIKE CONCAT('%',:query,'%')" +
			"Or p.description LIKE CONCAT('%', :query, '%')")
	List<Product> searchProducts(String query);
	
	//native sql query  here products is table name
	@Query(value = "SELECT * FROM Products p WHERE" + 
			"p.name LIKE CONCAT('%',:query,'%')" +
			"Or p.description LIKE CONCAT('%', :query, '%')",nativeQuery = true)
	List<Product> searchProductsSQL(String query);
}
