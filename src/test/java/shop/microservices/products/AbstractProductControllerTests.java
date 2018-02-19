package shop.microservices.products;

import java.math.BigDecimal;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import shop.microservices.exceptions.ProductNotFoundException;

public abstract class AbstractProductControllerTests {

	protected static final String Product_1_NAME = "Chair";
	protected static final BigDecimal Product_1_PRICE = new BigDecimal("50.00");

	@Autowired
	ProductsController productController;

	@Test
	public void validProduct() {
		Logger.getGlobal().info("Start validProductNumber test");
		Product Product = productController.byProductName(Product_1_NAME);

		Assert.assertNotNull(Product);
		Assert.assertEquals(Product_1_PRICE, Product.getPrice());
		Logger.getGlobal().info("End validProduct test");
	}
	
	@Test
	public void invalidProductName() {
		try {
			Logger.getGlobal().info("Start validProductNumber test");
			Product Product = productController.byProductName("yuis");
			Assert.fail("Expected an ProductNotFoundException");
		} catch (ProductNotFoundException e) {
			// Worked!
		}
	}

}
