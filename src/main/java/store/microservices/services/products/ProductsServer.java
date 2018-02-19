package store.microservices.services.products;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import store.microservices.products.ProductRepository;
import store.microservices.products.ProductsConfiguration;

/**
 * Run as a micro-service, registering with the Discovery Server (Eureka).
 * <p>
 * Note that the configuration for this application is imported from
 * {@link ProductsConfiguration}. This is a deliberate separation of concerns.
 * 
 * @author Ruslan Paluektau
 */
@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(ProductsConfiguration.class)
public class ProductsServer {

	@Autowired
	protected ProductRepository ProductRepository;

	protected Logger logger = Logger.getLogger(ProductsServer.class.getName());

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for Products-server.properties or
		// Products-server.yml
		System.setProperty("spring.config.name", "products-server");

		SpringApplication.run(ProductsServer.class, args);
	}
}
