package com.cognizant.productservice.main;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.cognizant.productservice.*")
@EnableJpaRepositories(basePackages = "com.cognizant.productservice.repositories")
@EntityScan(basePackages = "com.cognizant.productservice.entities")
@EnableDiscoveryClient(autoRegister=true)
@OpenAPIDefinition(
		info=@Info(
				title="Product Service REST API Documentation",
				description="REST API Documentation for Product Service",
				version="v1.0"
		)
)
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
}
