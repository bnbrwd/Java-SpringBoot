package com.example.springdatajpa.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(
		name = "products",
		schema = "ecommerce",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "sku")
		}
)
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //generate primary key
	private Long id;
	@Column(name = "stock_keeping_unit",nullable = false) //custom column name
	private String sku;
	@Column(nullable = false) //not null 
	private String name;
	private String description;
	private BigDecimal price;
	
	private boolean active;
	
	private String imageUrl;
	@CreationTimestamp
	private LocalDateTime dateCreated;
	@UpdateTimestamp
	private LocalDateTime lastUpdated;
}
