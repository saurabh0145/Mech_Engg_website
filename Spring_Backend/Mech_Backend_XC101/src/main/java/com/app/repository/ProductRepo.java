package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
	
	Product findByProductName(String productName);

}
