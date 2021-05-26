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
	public double findProductPriceByProductNameAndUnits(String name, int unitcount) {
		//Query the database and retrieve the details of the given product 
		//Assumption: Product names are unique
		final Query query = new Query();
		query.addCriteria(Criteria.where("productName").is(name));
		
		List<Product> products = mongoTemplate.find(query, Product.class);
		
		Product product = products.get(0);
		
		double price = 0;
		
		int numberOfCartons = unitcount/product.getUnitPerCarton();
		int numberOfLooseUnits = unitcount % product.getUnitPerCarton();
		
		double unitPrice = product.getPricePerCarton() / product.getUnitPerCarton();
		
		if(numberOfCartons > 0) {
			// If the number of cartons being bought is more than three give a 10% discount
			price = (numberOfCartons > 3 ? (numberOfCartons * product.getPricePerCarton()) * 0.9 : numberOfCartons * product.getPricePerCarton() );
		}
		if (numberOfLooseUnits > 0) {
			//If the customer attempts to buy a single unit, add a 30% labour charge to the final price
			price = (price == 0 && numberOfLooseUnits == 1)? (price + ( numberOfLooseUnits * unitPrice )) * 1.3 : price + ( numberOfLooseUnits * unitPrice );
		}
		
		return price;
	}

}
