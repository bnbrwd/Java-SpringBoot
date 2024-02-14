package com.example.springdatajpa.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	
	private String name;
	private String description;
	private String sku;
	private BigDecimal price;
	private boolean active;
	private String imageUrl;
}
