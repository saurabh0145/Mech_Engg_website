package com.app.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Product;
import com.app.service.IProductService;

@RestController
@RequestMapping("/product")
@Validated
public class ProductController {
	
	public ProductController(IProductService productService)
	{
		this.productService = productService;
	}
	
	private IProductService productService;
	
	@GetMapping
	private ResponseEntity<?> getProductByName(@RequestParam(name = "productName") String productName)
	{
		Product product =  productService.getProductByName(productName);
		if(product != null)
			return ResponseEntity.ok(product);
		 else 
			return new ResponseEntity<>("No product found", HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	private ResponseEntity<?> addProduct(@RequestBody @Valid Product prod)
	{
		Product product = productService.addProduct(prod);
		if(product !=null)
			return new ResponseEntity<>(product, HttpStatus.CREATED);
		return (ResponseEntity<?>) ResponseEntity.badRequest();
	}
	
	@GetMapping("/{productId}")
	private ResponseEntity<?> getProductById(@PathVariable Integer productId)
	{
		Optional<Product> product =  productService.getProductById(productId);
		if(product.isPresent())
			return ResponseEntity.ok(product.get());
		 else 
			return new ResponseEntity<>("No product found", HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/all")
	private ResponseEntity<?> getAllProducts()
	{
		List<Product> productList =  productService.getAllProducts();
		if(productList != null && productList.size() > 0)
			return ResponseEntity.ok(productList);
		 else 
			return new ResponseEntity<>("No product present", HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{productId}")
	private ResponseEntity<?> deleteProductById(@PathVariable Integer productId)
	{
		try
		{
			productService.removeProductById(productId);
			return new ResponseEntity<>("Product removed with ID : " +  productId  , HttpStatus.OK);
		}
		catch (NotFoundException e) {
			return new ResponseEntity<>("product Id not found : " + productId, HttpStatus.NOT_FOUND);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
		}
	}
	
}
