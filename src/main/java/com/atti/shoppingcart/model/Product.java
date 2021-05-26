package com.atti.shoppingcart.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {
	
	@Id
	private String productId;
	private String productName;
	private int unitPerCarton;
	private double pricePerCarton;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getUnitPerCarton() {
		return unitPerCarton;
	}
	public void setUnitPerCarton(int unitPerCarton) {
		this.unitPerCarton = unitPerCarton;
	}
	public double getPricePerCarton() {
		return pricePerCarton;
	}
	public void setPricePerCarton(double pricePerCarton) {
		this.pricePerCarton = pricePerCarton;
	}
	
	
	
}
