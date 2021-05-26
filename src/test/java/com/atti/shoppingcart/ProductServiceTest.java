package com.atti.shoppingcart;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.atti.shoppingcart.model.Product;
import com.atti.shoppingcart.repository.ProductRepository;
import com.atti.shoppingcart.service.ProductService;

/**
 * Service test for {@linkplain ProductService}
 * Test the return price for product with different quantities
 * @author Devni Attigala
 *
 */
class ProductServiceTest {
	
	private static ProductRepository productRepository = Mockito.mock(ProductRepository.class);
	
	private static ProductService productService;
	
	private  final String GOLDEN_EGG = "golden-egg";
	
	
	@BeforeAll
	public static void setUp() {
		productService = new ProductService(productRepository);
	}
	
	
	/**
	 * WHEN a customer checks price for a single unit of a product
	 * THEN the final price must include 30% manual labour fee
	 */
	@Test
	void it_returns_price_for_single_unit() {
		Mockito.when(productRepository.findProductPriceByProductName(GOLDEN_EGG)).thenReturn(createDummyProduct());
		
		double actualPrice =  productService.getPricePerProduct(GOLDEN_EGG, 1);
		
		double expected = 2.0 * 1.3;
		
		assertEquals(expected, actualPrice, 0.01);
		
	}
	

	/**
	 * WHEN a customer checks price of three cartons of a product
	 * THEN the final price must include a 10% discount
	 */
	@Test
	public void it_return_discounted_price_for_three_cartons() {
		Mockito.when(productRepository.findProductPriceByProductName(GOLDEN_EGG)).thenReturn(createDummyProduct());
		
		double actualPrice = productService.getPricePerProduct(GOLDEN_EGG, 15);
		
		double expectedPrice = (3 * 10.0) * 0.9;
		
		assertEquals(expectedPrice, actualPrice, 0.01);
	}
	
	
	/**
	 * WHEN a customer checks price for 18 units
	 * THEN the final price should be an addition of the price of three cartons (with discounts) 
	 * and the price of three loose units
	 */
	@Test
	public void it_return_discounted_price_for_three_cartons_and_two_units() {
		Mockito.when(productRepository.findProductPriceByProductName(GOLDEN_EGG)).thenReturn(createDummyProduct());
		
		double actualPrice = productService.getPricePerProduct(GOLDEN_EGG, 18);
		
		double expectedPrice = ((3 * 10.0) * 0.9 ) + 2.0 *3;
		
		assertEquals(expectedPrice, actualPrice, 0.01);
	}
	public Product createDummyProduct() {
		Product product = new Product();
		product.setProductId("P001");
		product.setProductName(GOLDEN_EGG);
		product.setPricePerCarton(10.0);
		product.setUnitPerCarton(5);
		return product;
	}

}
