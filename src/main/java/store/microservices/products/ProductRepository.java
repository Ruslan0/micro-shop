package store.microservices.products;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

/**
 * Repository for product data implemented using Spring Data JPA.
 * 
 * @author Ruslan Paluektau
 */
public interface ProductRepository extends Repository<Product, Long> {
	/**
	 * Find an product with the specified product number.
	 *
	 * @param productName
	 * @return The product if found, null otherwise.
	 */
	public Product findByProductName(String productName);

	/**
	 * Fetch the number of products known to the system.
	 * 
	 * @return The number of products.
	 */
	@Query("SELECT count(*) from Product")
	public int countProducts();
}
