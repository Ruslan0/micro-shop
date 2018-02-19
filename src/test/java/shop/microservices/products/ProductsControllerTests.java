package shop.microservices.products;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import shop.microservices.products.Product;
import shop.microservices.products.ProductRepository;
import shop.microservices.products.ProductsController;

public class ProductsControllerTests extends AbstractProductControllerTests {

	protected static final Product theProduct = new Product(Product_1_NAME, Product_1_PRICE);

	protected static class TestProductRepository implements ProductRepository {

		@Override
		public Product findByProductName(String productName) {
			if (productName.equals(Product_1_NAME))
				return theProduct;
			else
				return null;
		}

		@Override
		public int countProducts() {
			return 1;
		}

	}

	protected TestProductRepository testRepo = new TestProductRepository();

	@Before
	public void setup() {
		productController = new ProductsController(testRepo);
	}
}
