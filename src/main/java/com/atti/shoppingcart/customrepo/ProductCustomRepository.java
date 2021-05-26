package com.atti.shoppingcart.customrepo;

public interface ProductCustomRepository {
	/**
	 * Queries the database for the given product details and determine the final price
	 * based on the number of units to be bought.
	 * If the quantity is a single unit then a 30% charge will be added.
	 * Otherwise price will be calculated for cartons and remaining units.
	 * @param name 
	 * @param unitcount
	 * @return price
	 */
	public double findProductPriceByProductNameAndUnits(String name, int unitcount);
}
