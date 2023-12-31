package com.app.model;

import javax.validation.constraints.NotBlank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_Id")
	private Integer id;
	
	@Column(length = 20,name = "product_name")
	@NotBlank(message = "productName should not be null")
	private String productName;
	
	@Column(length = 200,name = "product_desc")
	private String productDescription;
	
	@Column(length = 200,name = "product_price(in Rs.)")
	private String productCost;
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", productDescription=" + productDescription
				+ ", productCost=" + productCost + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductCost() {
		return productCost;
	}

	public void setProductCost(String productCost) {
		this.productCost = productCost;
	}

}
