package store.microservices.products;

import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

/**
 * The products Spring configuration.
 * 
 * @author Ruslan Paluektau
 */
@Configuration
@ComponentScan
@EntityScan("store.microservices.products")
@EnableJpaRepositories("store.microservices.products")
@PropertySource("classpath:db-config.properties")
public class ProductsConfiguration {

	protected Logger logger;

	public ProductsConfiguration() {
		logger = Logger.getLogger(getClass().getName());
	}

	/**
	 * Creates an in-memory "rewards" database populated with test data for fast
	 * testing
	 */
	@Bean
	public DataSource dataSource() {
		logger.info("dataSource() invoked");

		// Create an in-memory H2 relational database containing some demo
		// products.
		DataSource dataSource = (new EmbeddedDatabaseBuilder()).addScript("classpath:testdb/schema_prod.sql")
				.addScript("classpath:testdb/data_prod.sql").build();

		logger.info("dataSource = " + dataSource);

		return dataSource;
	}
}
