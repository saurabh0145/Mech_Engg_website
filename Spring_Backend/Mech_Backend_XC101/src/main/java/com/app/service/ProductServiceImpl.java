package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.app.model.Product;
import com.app.repository.ProductRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {
	
	@Autowired
	private ProductRepo productRepo;

	@Override
	public Product getProductByName(String productName) {
		return productRepo.findByProductName(productName);
	}

	@Override
	public Product addProduct(Product product) {
		return productRepo.save(product);
	}

	@Override
	public Optional<Product> getProductById(Integer productId) {
		return productRepo.findById(productId);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepo.findAll();	
	}

	@Override
	public void removeProductById(Integer id) throws NotFoundException {
		if(!productRepo.findById(id).isEmpty())
			productRepo.deleteById(id);
		else
			throw new NotFoundException();
	}

}
