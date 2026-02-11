package com.cognizant.productservice.controllers;

import com.cognizant.productservice.dtos.ProductDTO;
import com.cognizant.productservice.main.ProductServiceApplication;
import com.cognizant.productservice.services.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(classes= ProductServiceApplication.class)
@ActiveProfiles("test")
public class TestProductServiceController {
    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductServiceController productServiceController;

    @Autowired
    private LocalValidatorFactoryBean validator;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {

    }

    @Test
    public void testGetAllProductsPositiveAssertReturnValue() {
        List<ProductDTO> productDTOList=new ArrayList<>();

        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        productDTOList.add(productDTO);

        try {
            when(productService.getAllProducts()).thenReturn(productDTOList);
            ResponseEntity<List<ProductDTO>> responseEntity=productServiceController.getAllProducts();
            List<ProductDTO> actualProductDTOList=responseEntity.getBody();
            assertTrue(actualProductDTOList.size()>0);
        }catch(Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetAllProductsPositiveAssertStatusCode() {
        List<ProductDTO> productDTOList=new ArrayList<>();

        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        productDTOList.add(productDTO);

        try {
            when(productService.getAllProducts()).thenReturn(productDTOList);
            ResponseEntity<List<ProductDTO>> responseEntity=productServiceController.getAllProducts();
            assertEquals(200,responseEntity.getStatusCode().value());
        }catch(Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetAllProductsNegativeAssertReturnValue() {
        List<ProductDTO> productDTOList=new ArrayList<>();
        try {
            when(productService.getAllProducts()).thenReturn(productDTOList);
            ResponseEntity<List<ProductDTO>> responseEntity=productServiceController.getAllProducts();
            assertNull(responseEntity.getBody());
        }catch(Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetAllProductsNegativeAssertStatusCode() {
        List<ProductDTO> productDTOList=new ArrayList<>();
        try {
            when(productService.getAllProducts()).thenReturn(productDTOList);
            ResponseEntity<List<ProductDTO>> responseEntity=productServiceController.getAllProducts();
            assertEquals(400,responseEntity.getStatusCode().value());
        }catch(Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetProductsByNamePositiveAssertReturnValue() {
        List<ProductDTO> productDTOList=new ArrayList<>();

        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        productDTOList.add(productDTO);

        try {
            when(productService.getProductsByName(anyString())).thenReturn(productDTOList);
            ResponseEntity<List<ProductDTO>> responseEntity=productServiceController.getProductsByName("Portable SSD 1TB");
            List<ProductDTO> actualProductDTOList=responseEntity.getBody();
            assertTrue(actualProductDTOList.size()>0);
        }catch(Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetProductsByNamePositiveAssertStatusCode() {
        List<ProductDTO> productDTOList=new ArrayList<>();

        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        productDTOList.add(productDTO);

        try {
            when(productService.getProductsByName(anyString())).thenReturn(productDTOList);
            ResponseEntity<List<ProductDTO>> responseEntity=productServiceController.getProductsByName("Portable SSD 1TB");
            assertEquals(200,responseEntity.getStatusCode().value());
        }catch(Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetProductsByNameNegativeAssertReturnValue() {
        List<ProductDTO> productDTOList=new ArrayList<>();
        try {
            when(productService.getProductsByName(anyString())).thenReturn(productDTOList);
            ResponseEntity<List<ProductDTO>> responseEntity=productServiceController.getProductsByName("Portable SSD 1TB");
            assertNull(responseEntity.getBody());
        }catch(Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetProductsByNameNegativeAssertStatusCode() {
        List<ProductDTO> productDTOList=new ArrayList<>();
        try {
            when(productService.getProductsByName(anyString())).thenReturn(productDTOList);
            ResponseEntity<List<ProductDTO>> responseEntity=productServiceController.getProductsByName("Portable SSD 1TB");
            assertEquals(400,responseEntity.getStatusCode().value());
        }catch(Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testDeleteProductPositiveAssertReturnValue() {
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        try {
            when(productService.deleteProduct(any())).thenReturn("Product deleted with Id: " + productDTO.getId());
            ResponseEntity<String> responseEntity=productServiceController.deleteProduct(1L);
            String result=responseEntity.getBody();
            assertNotNull(result);
        }catch(Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetDeletePositiveAssertStatusCode() {
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        try {
            when(productService.deleteProduct(any())).thenReturn("Product deleted with Id: " + productDTO.getId());
            ResponseEntity<String> responseEntity=productServiceController.deleteProduct(1L);
            assertEquals(200,responseEntity.getStatusCode().value());
        }catch(Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testDeleteUserNegativeAssertReturnValue() {
        String result=null;
        try {
            when(productService.deleteProduct(any())).thenReturn(result);
            ResponseEntity<String> responseEntity=productServiceController.deleteProduct(1L);
            assertNull(responseEntity.getBody());
        }catch(Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testDeleteUserNegativeAssertStatusCode() {
        String result=null;
        try {
            when(productService.deleteProduct(any())).thenReturn(result);
            ResponseEntity<String> responseEntity=productServiceController.deleteProduct(1L);
            assertEquals(400,responseEntity.getStatusCode().value());
        }catch(Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetProductPositiveAssertReturnValue() {
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        try {
            when(productService.getProduct(any())).thenReturn(productDTO);
            ResponseEntity<ProductDTO> responseEntity=productServiceController.getProduct(1L);
            ProductDTO actualProductDTO=responseEntity.getBody();
            assertNotNull(actualProductDTO);
        }catch(Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetProductPositiveAssertStatusCode() {
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        try {
            when(productService.getProduct(any())).thenReturn(productDTO);
            ResponseEntity<ProductDTO> responseEntity=productServiceController.getProduct(1L);
            assertEquals(200,responseEntity.getStatusCode().value());
        }catch(Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetProductNegativeAssertReturnValue() {
        ProductDTO productDTO=null;
        try {
            when(productService.getProduct(any())).thenReturn(productDTO);
            ResponseEntity<ProductDTO> responseEntity=productServiceController.getProduct(1L);
            assertNull(responseEntity.getBody());
        }catch(Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetProductNegativeAssertStatusCode() {
        ProductDTO productDTO=null;
        try {
            when(productService.getProduct(any())).thenReturn(productDTO);
            ResponseEntity<ProductDTO> responseEntity=productServiceController.getProduct(1L);
            assertEquals(400,responseEntity.getStatusCode().value());
        }catch(Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testAddProductWhenProductIsValid() {
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        validator.validate(productDTO).stream().forEach((constraintViolation)->assertNull(constraintViolation));
    }

    @Test
    public void testAddProductPositiveAssertReturnValue() {
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        try {
            when(productService.createProduct(any(ProductDTO.class))).thenReturn(productDTO);
            ResponseEntity<ProductDTO> responseEntity=productServiceController.createProduct(productDTO);
            ProductDTO actualProductDTO=responseEntity.getBody();
            assertNotNull(actualProductDTO);
        }catch(Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testAddProductPositiveAssertStatusCode() {
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        try {
            when(productService.createProduct(any(ProductDTO.class))).thenReturn(productDTO);
            ResponseEntity<ProductDTO> responseEntity=productServiceController.createProduct(productDTO);
            assertEquals(201,responseEntity.getStatusCode().value());
        }catch(Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testAddProductWhenProductIsNotValid() {
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("");
        productDTO.setDescription("");
        productDTO.setPrice(-3099);
        productDTO.setStock(-200);

        validator.validate(productDTO).stream().forEach((constraintViolation)->assertNotNull(constraintViolation));
    }

    @Test
    public void testAddProductNegativeAssertReturnValue() {
        ProductDTO productDTO=null;

        try {
            when(productService.createProduct(any(ProductDTO.class))).thenReturn(productDTO);
            ResponseEntity<ProductDTO> responseEntity=productServiceController.createProduct(productDTO);
            ProductDTO actualProductDTO=responseEntity.getBody();
            assertNull(actualProductDTO);
        }catch(Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testAddProductNegativeAssertStatusCode() {
        ProductDTO productDTO=null;

        try {
            when(productService.createProduct(any(ProductDTO.class))).thenReturn(productDTO);
            ResponseEntity<ProductDTO> responseEntity=productServiceController.createProduct(productDTO);
            assertEquals(400,responseEntity.getStatusCode().value());
        }catch(Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testUpdateProductWhenProductIsValid() {
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        validator.validate(productDTO).stream().forEach((constraintViolation)->assertNull(constraintViolation));
    }

    @Test
    public void testUpdateProductPositiveAssertReturnValue() {
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        try {
            when(productService.updateProduct(any(),any(ProductDTO.class))).thenReturn(productDTO);
            ResponseEntity<ProductDTO> responseEntity=productServiceController.updateProduct(1L,productDTO);
            ProductDTO actualProductDTO=responseEntity.getBody();
            assertNotNull(actualProductDTO);
        }catch(Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testUpdateProductPositiveAssertStatusCode() {
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        try {
            when(productService.updateProduct(any(),any(ProductDTO.class))).thenReturn(productDTO);
            ResponseEntity<ProductDTO> responseEntity=productServiceController.updateProduct(1L,productDTO);
            assertEquals(202,responseEntity.getStatusCode().value());
        }catch(Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testUpdateProductWhenProductIsNotValid() {
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("");
        productDTO.setDescription("");
        productDTO.setPrice(-3099);
        productDTO.setStock(-200);

        validator.validate(productDTO).stream().forEach((constraintViolation)->assertNotNull(constraintViolation));
    }

    @Test
    public void testUpdateProductNegativeAssertReturnValue() {
        ProductDTO productDTO=null;

        try {
            when(productService.updateProduct(any(),any(ProductDTO.class))).thenReturn(productDTO);
            ResponseEntity<ProductDTO> responseEntity=productServiceController.updateProduct(1L,productDTO);
            ProductDTO actualProductDTO=responseEntity.getBody();
            assertNull(actualProductDTO);
        }catch(Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testUpdateUserNegativeAssertStatusCode() {
        ProductDTO productDTO=null;

        try {
            when(productService.updateProduct(any(),any(ProductDTO.class))).thenReturn(productDTO);
            ResponseEntity<ProductDTO> responseEntity=productServiceController.updateProduct(1L,productDTO);
            assertEquals(400,responseEntity.getStatusCode().value());
        }catch(Exception e) {
            assertTrue(false);
        }
    }


}
