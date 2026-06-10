package com.cognizant.productservice.test.controllers;

import com.cognizant.productservice.controllers.ProductServiceController;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ProductServiceApplication.class)
@ActiveProfiles("test")
class TestProductServiceController {

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
    void testGetAllProductsPositiveAssertReturnValue() {
        List<ProductDTO> productDTOList = new ArrayList<>();

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        productDTOList.add(productDTO);

        try {
            when(productService.getAllProducts()).thenReturn(productDTOList);
            ResponseEntity<List<ProductDTO>> responseEntity = productServiceController.getAllProducts();
            List<ProductDTO> actualProductDTOList = responseEntity.getBody();
            assertTrue(actualProductDTOList.size() > 0);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testGetAllProductsPositiveAssertStatusCode() {
        List<ProductDTO> productDTOList = new ArrayList<>();

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        productDTOList.add(productDTO);

        try {
            when(productService.getAllProducts()).thenReturn(productDTOList);
            ResponseEntity<List<ProductDTO>> responseEntity = productServiceController.getAllProducts();
            assertEquals(200, responseEntity.getStatusCode().value());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testGetAllProductsNegativeAssertReturnValue() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        try {
            when(productService.getAllProducts()).thenReturn(productDTOList);
            ResponseEntity<List<ProductDTO>> responseEntity = productServiceController.getAllProducts();
            assertNull(responseEntity.getBody());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testGetAllProductsNegativeAssertStatusCode() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        try {
            when(productService.getAllProducts()).thenReturn(productDTOList);
            ResponseEntity<List<ProductDTO>> responseEntity = productServiceController.getAllProducts();
            assertEquals(400, responseEntity.getStatusCode().value());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testGetProductsByNamePositiveAssertReturnValue() {
        List<ProductDTO> productDTOList = new ArrayList<>();

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        productDTOList.add(productDTO);

        try {
            when(productService.getProductsByName(anyString())).thenReturn(productDTOList);
            ResponseEntity<List<ProductDTO>> responseEntity = productServiceController.getProductsByName("Portable SSD 1TB");
            List<ProductDTO> actualProductDTOList = responseEntity.getBody();
            assertTrue(actualProductDTOList.size() > 0);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testGetProductsByNamePositiveAssertStatusCode() {
        List<ProductDTO> productDTOList = new ArrayList<>();

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        productDTOList.add(productDTO);

        try {
            when(productService.getProductsByName(anyString())).thenReturn(productDTOList);
            ResponseEntity<List<ProductDTO>> responseEntity = productServiceController.getProductsByName("Portable SSD 1TB");
            assertEquals(200, responseEntity.getStatusCode().value());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testGetProductsByNameNegativeAssertReturnValue() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        try {
            when(productService.getProductsByName(anyString())).thenReturn(productDTOList);
            ResponseEntity<List<ProductDTO>> responseEntity = productServiceController.getProductsByName("Portable SSD 1TB");
            assertNull(responseEntity.getBody());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testGetProductsByNameNegativeAssertStatusCode() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        try {
            when(productService.getProductsByName(anyString())).thenReturn(productDTOList);
            ResponseEntity<List<ProductDTO>> responseEntity = productServiceController.getProductsByName("Portable SSD 1TB");
            assertEquals(400, responseEntity.getStatusCode().value());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testDeleteProductPositiveAssertReturnValue() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        try {
            when(productService.deleteProduct(any())).thenReturn("Product deleted with Id: " + productDTO.getId());
            ResponseEntity<String> responseEntity = productServiceController.deleteProduct(1L);
            String result = responseEntity.getBody();
            assertNotNull(result);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testDeleteProductPositiveAssertStatusCode() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        try {
            when(productService.deleteProduct(any())).thenReturn("Product deleted with Id: " + productDTO.getId());
            ResponseEntity<String> responseEntity = productServiceController.deleteProduct(1L);
            assertEquals(200, responseEntity.getStatusCode().value());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testDeleteProductNegativeAssertReturnValue() {
        String result = null;
        try {
            when(productService.deleteProduct(any())).thenReturn(result);
            ResponseEntity<String> responseEntity = productServiceController.deleteProduct(1L);
            assertNull(responseEntity.getBody());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testDeleteProductNegativeAssertStatusCode() {
        String result = null;
        try {
            when(productService.deleteProduct(any())).thenReturn(result);
            ResponseEntity<String> responseEntity = productServiceController.deleteProduct(1L);
            assertEquals(400, responseEntity.getStatusCode().value());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testGetProductPositiveAssertReturnValue() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        try {
            when(productService.getProduct(any())).thenReturn(productDTO);
            ResponseEntity<ProductDTO> responseEntity = productServiceController.getProduct(1L);
            ProductDTO actualProductDTO = responseEntity.getBody();
            assertNotNull(actualProductDTO);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testGetProductPositiveAssertStatusCode() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        try {
            when(productService.getProduct(any())).thenReturn(productDTO);
            ResponseEntity<ProductDTO> responseEntity = productServiceController.getProduct(1L);
            assertEquals(200, responseEntity.getStatusCode().value());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testGetProductNegativeAssertReturnValue() {
        ProductDTO productDTO = null;
        try {
            when(productService.getProduct(any())).thenReturn(productDTO);
            ResponseEntity<ProductDTO> responseEntity = productServiceController.getProduct(1L);
            assertNull(responseEntity.getBody());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testGetProductNegativeAssertStatusCode() {
        ProductDTO productDTO = null;
        try {
            when(productService.getProduct(any())).thenReturn(productDTO);
            ResponseEntity<ProductDTO> responseEntity = productServiceController.getProduct(1L);
            assertEquals(400, responseEntity.getStatusCode().value());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testAddProductWhenProductIsValid() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        validator.validate(productDTO).stream().forEach((constraintViolation) -> assertNull(constraintViolation));
    }

    @Test
    void testAddProductPositiveAssertReturnValue() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        try {
            when(productService.createProduct(any(ProductDTO.class))).thenReturn(productDTO);
            ResponseEntity<ProductDTO> responseEntity = productServiceController.createProduct(productDTO);
            ProductDTO actualProductDTO = responseEntity.getBody();
            assertNotNull(actualProductDTO);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testAddProductPositiveAssertStatusCode() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        try {
            when(productService.createProduct(any(ProductDTO.class))).thenReturn(productDTO);
            ResponseEntity<ProductDTO> responseEntity = productServiceController.createProduct(productDTO);
            assertEquals(201, responseEntity.getStatusCode().value());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testAddProductWhenProductIsNotValid() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("");
        productDTO.setDescription("");
        productDTO.setPrice(-3099);
        productDTO.setStock(-200);

        validator.validate(productDTO).stream().forEach((constraintViolation) -> assertNotNull(constraintViolation));
    }

    @Test
    void testAddProductNegativeAssertReturnValue() {
        ProductDTO productDTO = null;

        try {
            when(productService.createProduct(any(ProductDTO.class))).thenReturn(productDTO);
            ResponseEntity<ProductDTO> responseEntity = productServiceController.createProduct(productDTO);
            ProductDTO actualProductDTO = responseEntity.getBody();
            assertNull(actualProductDTO);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testAddProductNegativeAssertStatusCode() {
        ProductDTO productDTO = null;

        try {
            when(productService.createProduct(any(ProductDTO.class))).thenReturn(productDTO);
            ResponseEntity<ProductDTO> responseEntity = productServiceController.createProduct(productDTO);
            assertEquals(400, responseEntity.getStatusCode().value());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testUpdateProductWhenProductIsValid() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        validator.validate(productDTO).stream().forEach((constraintViolation) -> assertNull(constraintViolation));
    }

    @Test
    void testUpdateProductPositiveAssertReturnValue() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        try {
            when(productService.updateProduct(any(), any(ProductDTO.class))).thenReturn(productDTO);
            ResponseEntity<ProductDTO> responseEntity = productServiceController.updateProduct(1L, productDTO);
            ProductDTO actualProductDTO = responseEntity.getBody();
            assertNotNull(actualProductDTO);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testUpdateProductPositiveAssertStatusCode() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        try {
            when(productService.updateProduct(any(), any(ProductDTO.class))).thenReturn(productDTO);
            ResponseEntity<ProductDTO> responseEntity = productServiceController.updateProduct(1L, productDTO);
            assertEquals(202, responseEntity.getStatusCode().value());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testUpdateProductWhenProductIsNotValid() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("");
        productDTO.setDescription("");
        productDTO.setPrice(-3099);
        productDTO.setStock(-200);

        validator.validate(productDTO).stream().forEach((constraintViolation) -> assertNotNull(constraintViolation));
    }

    @Test
    void testUpdateProductNegativeAssertReturnValue() {
        ProductDTO productDTO = null;

        try {
            when(productService.updateProduct(any(), any(ProductDTO.class))).thenReturn(productDTO);
            ResponseEntity<ProductDTO> responseEntity = productServiceController.updateProduct(1L, productDTO);
            ProductDTO actualProductDTO = responseEntity.getBody();
            assertNull(actualProductDTO);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testUpdateProductNegativeAssertStatusCode() {
        ProductDTO productDTO = null;

        try {
            when(productService.updateProduct(any(), any(ProductDTO.class))).thenReturn(productDTO);
            ResponseEntity<ProductDTO> responseEntity = productServiceController.updateProduct(1L, productDTO);
            assertEquals(400, responseEntity.getStatusCode().value());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testGetProductPositiveAssertName() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        try {
            when(productService.getProduct(any())).thenReturn(productDTO);
            ResponseEntity<ProductDTO> responseEntity = productServiceController.getProduct(1L);
            assertEquals("Portable SSD 1TB", responseEntity.getBody().getName());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testGetProductPositiveAssertPrice() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        try {
            when(productService.getProduct(any())).thenReturn(productDTO);
            ResponseEntity<ProductDTO> responseEntity = productServiceController.getProduct(1L);
            assertEquals(3099, responseEntity.getBody().getPrice());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testGetProductsByNamePositiveAssertListSize() {
        List<ProductDTO> productDTOList = new ArrayList<>();

        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setId(1L);
        productDTO1.setName("Mechanical Keyboard");
        productDTO1.setDescription("RGB backlit mechanical keyboard with blue switches.");
        productDTO1.setPrice(5099);
        productDTO1.setStock(200);

        ProductDTO productDTO2 = new ProductDTO();
        productDTO2.setId(2L);
        productDTO2.setName("Mechanical Keyboard");
        productDTO2.setDescription("Compact tenkeyless mechanical keyboard.");
        productDTO2.setPrice(4599);
        productDTO2.setStock(150);

        productDTOList.add(productDTO1);
        productDTOList.add(productDTO2);

        try {
            when(productService.getProductsByName(anyString())).thenReturn(productDTOList);
            ResponseEntity<List<ProductDTO>> responseEntity = productServiceController.getProductsByName("Mechanical Keyboard");
            assertEquals(2, responseEntity.getBody().size());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testAddProductPositiveAssertName() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Portable SSD 1TB");
        productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        productDTO.setPrice(3099);
        productDTO.setStock(200);

        try {
            when(productService.createProduct(any(ProductDTO.class))).thenReturn(productDTO);
            ResponseEntity<ProductDTO> responseEntity = productServiceController.createProduct(productDTO);
            assertEquals("Portable SSD 1TB", responseEntity.getBody().getName());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testDeleteProductPositiveAssertMessage() {
        try {
            when(productService.deleteProduct(any())).thenReturn("Product deleted with Id: 1");
            ResponseEntity<String> responseEntity = productServiceController.deleteProduct(1L);
            assertEquals("Product deleted with Id: 1", responseEntity.getBody());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testUpdateProductPositiveAssertUpdatedName() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Updated Keyboard");
        productDTO.setDescription("Updated description.");
        productDTO.setPrice(5500);
        productDTO.setStock(150);

        try {
            when(productService.updateProduct(any(), any(ProductDTO.class))).thenReturn(productDTO);
            ResponseEntity<ProductDTO> responseEntity = productServiceController.updateProduct(1L, productDTO);
            assertEquals("Updated Keyboard", responseEntity.getBody().getName());
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
