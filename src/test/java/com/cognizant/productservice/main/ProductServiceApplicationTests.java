package com.cognizant.productservice.main;

import com.cognizant.productservice.controllers.ProductServiceController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes= ProductServiceApplication.class)
@ActiveProfiles("test")
class ProductServiceApplicationTests {
	@Autowired
	private ProductServiceController productServiceController;

	@Test
	void contextLoads() {
		assertNotNull(productServiceController);
	}

}
