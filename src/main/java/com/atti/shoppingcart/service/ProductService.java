package com.atti.shoppingcart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atti.shoppingcart.model.Product;
import com.atti.shoppingcart.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}
	
	public double getPricePerProduct(String productName, int unitCount) {
		return productRepository.findProductPriceByProductNameAndUnits(productName, unitCount);
	}
	
	public List<Product> findAll(){
		return productRepository.findAll();
	}
}
