package store.microservices.products;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import store.microservices.exceptions.ProductNotFoundException;

/**
 * A RESTFul controller for accessing Product information.
 * 
 * @author Ruslan Paluektau
 */
@RestController
public class ProductsController {

	protected Logger logger = Logger.getLogger(ProductsController.class
			.getName());
	protected ProductRepository ProductRepository;

	/**
	 * Create an instance plugging in the respository of Products.
	 * 
	 * @param ProductRepository
	 *            An Product repository implementation.
	 */
	@Autowired
	public ProductsController(ProductRepository ProductRepository) {
		this.ProductRepository = ProductRepository;

		logger.info("ProductRepository says system has "
				+ ProductRepository.countProducts() + " Products");
	}

	/**
	 * Fetch an Product with the specified Product name.
	 * 
	 * @param productName
	 *            A String, 50 symbols Product name.
	 * @return The Product if found.
	 * @throws ProductNotFoundException
	 *             If the name is not recognised.
	 */
	@RequestMapping("/products/{productName}")
	public Product byProductName(@PathVariable("productName") String productName) {

		logger.info("Products-service byName() invoked: " + productName);
		Product Product = ProductRepository.findByProductName(productName);
		logger.info("Products-service byName() found: " + Product);

		if (Product == null)
			throw new ProductNotFoundException(productName);
		else {
			return Product;
		}
	}

}
