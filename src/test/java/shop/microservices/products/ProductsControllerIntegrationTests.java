package shop.microservices.products;

import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import shop.microservices.products.ProductsConfiguration;
import shop.microservices.services.products.ProductsServer;

/**
 * Imitates the {@link ProductsServer}, but without using any of the discovery
 * client code. Allows the test to use the same configuration as the
 * <code>ProductsServer</code> would.
 * 
 * @author Ruslan Paluektau
 *
 */
@SpringBootApplication
@Import(ProductsConfiguration.class)
class ProductsMain {
	public static void main(String[] args) {
		// Tell server to look for Products-server.properties or
		// Products-server.yml
		System.setProperty("spring.config.name", "Products-server");
		SpringApplication.run(ProductsMain.class, args);
	}
}

/**
 * Spring Integration/System test - by using @SpringApplicationConfiguration
 * instead of @ContextConfiguration, it picks up the same configuration that
 * Spring Boot would use.
 * 
 * @author Ruslan Paluektau
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProductsMain.class)
public class ProductsControllerIntegrationTests extends AbstractProductControllerTests {

}
