package com.atti.shoppingcart.customrepo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.atti.shoppingcart.customrepo.ProductCustomRepository;
import com.atti.shoppingcart.model.Product;

public class ProductCustomRepositoryImpl implements ProductCustomRepository {
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public Product findProductPriceByProductName(String name) {
		//Query the database and retrieve the details of the given product 
		//Assumption: Product names are unique
		final Query query = new Query();
		query.addCriteria(Criteria.where("productName").is(name));
		
		List<Product> products = mongoTemplate.find(query, Product.class);
		
		Product product = products.get(0);
		
		return product;
	}

	@Override
	public List<Product> findAllWithDerivedAttributes() {
		// TODO Auto-generated method stub
		List<Product> products = mongoTemplate.findAll(Product.class);
		
		return products;
	}

}
