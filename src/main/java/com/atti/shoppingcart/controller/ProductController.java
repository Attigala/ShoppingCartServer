package com.atti.shoppingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.atti.shoppingcart.model.Product;
import com.atti.shoppingcart.service.ProductService;

@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
	public Product add(@RequestBody Product product) {
		return productService.addProduct(product);
	}
	
	@GetMapping
	public double getProductPriceByNameAndQuantity(@RequestParam String productname, @RequestParam int unitcount) {
		return productService.getPricePerProduct(productname, unitcount);
	}
	@GetMapping("/all")
	public List<Product> getAllProducts(){
		System.out.println("request recieved");
		return productService.findAll();
	}
}
