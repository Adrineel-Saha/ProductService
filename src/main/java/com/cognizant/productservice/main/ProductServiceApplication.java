package com.cognizant.productservice.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.cognizant.productservice.*")
@EnableJpaRepositories(basePackages = "com.cognizant.productservice.repositories")
@EntityScan(basePackages = "com.cognizant.productservice.entities")
@EnableDiscoveryClient(autoRegister=true)
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
