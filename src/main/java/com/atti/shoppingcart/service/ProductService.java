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

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	public double getPricePerProduct(String productName, int unitCount) {
		Product product = productRepository.findProductPriceByProductName(productName);
		
		double price = 0;

		int numberOfCartons = unitCount / product.getUnitPerCarton();
		int numberOfLooseUnits = unitCount % product.getUnitPerCarton();

		double unitPrice = product.getPricePerCarton() / product.getUnitPerCarton();

		if (numberOfCartons > 0) {
			// If the number of cartons being bought is more than three give a 10% discount
			price = (numberOfCartons >= 3 ? (numberOfCartons * product.getPricePerCarton()) * 0.9
					: numberOfCartons * product.getPricePerCarton());
		}
		if (numberOfLooseUnits > 0) {
			// If the customer attempts to buy a single unit, add a 30% labour charge to the
			// final price
			price = (price == 0 && numberOfLooseUnits == 1) ? (price + (numberOfLooseUnits * unitPrice)) * 1.3
					: price + (numberOfLooseUnits * unitPrice);
		}
		return price;
	}

	public List<Product> findAll() {
		return productRepository.findAllWithPricePerFiftyUnits();
	}
}
