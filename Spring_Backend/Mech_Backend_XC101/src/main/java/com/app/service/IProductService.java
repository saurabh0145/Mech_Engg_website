package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.app.model.Product;

public interface IProductService {
	Product getProductByName(String productName);
	Product addProduct(Product product);
	Optional<Product> getProductById(Integer productId);
	List<Product> getAllProducts();
	void removeProductById(Integer id) throws NotFoundException;
}
