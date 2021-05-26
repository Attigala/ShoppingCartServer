package com.atti.shoppingcart.customrepo;

import com.atti.shoppingcart.model.Product;

public interface ProductCustomRepository {
	/**
	 * Queries the database for the given product 
	 * @param name 
	 * @param unitcount
	 * @return Product
	 */
	public Product findProductPriceByProductName(String name);
}
