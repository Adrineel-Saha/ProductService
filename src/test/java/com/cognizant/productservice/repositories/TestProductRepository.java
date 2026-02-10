package com.cognizant.productservice.repositories;

import com.cognizant.productservice.entities.Product;
import com.cognizant.productservice.main.ProductServiceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ContextConfiguration(classes = ProductServiceApplication.class)
@ActiveProfiles("test")
public class TestProductRepository {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testFindAllPositive(){
        Product product=new Product();
        product.setName("Mechanical Keyboard");
        product.setDescription("RGB backlit mechanical keyboard with blue switches.");
        product.setPrice(5099);
        product.setStock(200);

        entityManager.persist(product);

        List<Product> productList = productRepository.findAll();
        assertTrue(productList.iterator().hasNext());
    }

    @Test
    public void testFindAllNegative(){
        List<Product> productList = productRepository.findAll();
        assertTrue(!productList.iterator().hasNext());
    }

    @Test
    public void testFindByIdPositive(){
        Product product=new Product();
        product.setName("Wireless Mouse");
        product.setDescription("Ergonomic 2.4GHz wireless mouse with adjustable DPI.");
        product.setPrice(4099);
        product.setStock(300);

        entityManager.persist(product);
        Long id=product.getId();

        Optional<Product> productOptional=productRepository.findById(id);
        assertTrue(productOptional.isPresent());
    }

    @Test
    public void testFindByIdNegative(){
        Optional<Product> productOptional=productRepository.findById(2L);
        assertTrue(!productOptional.isPresent());
    }

    @Test
    public void testSavePositive(){
        Product product=new Product();
        product.setName("Portable SSD 1TB");
        product.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
        product.setPrice(3099);
        product.setStock(200);

        productRepository.save(product);
        Long id=product.getId();

        Optional<Product> productOptional=productRepository.findById(id);
        assertTrue(productOptional.isPresent());
    }

    @Test
    public void testSaveNegative(){
        Optional<Product> productOptional=productRepository.findById(3L);
        assertTrue(!productOptional.isPresent());
    }

    @Test
    public void deletePositive(){
        Product product=new Product();
        product.setName("USB-B Hub");
        product.setDescription("6-in-1 USB-b hub with HDMI, USB 3.0, SD/TF reader.");
        product.setPrice(3599);
        product.setStock(250);

        entityManager.persist(product);
        Long id=product.getId();

        productRepository.delete(product);
        Optional<Product> productOptional=productRepository.findById(id);
        assertTrue(!productOptional.isPresent());
    }

    @Test
    public void deleteNegative(){
        Optional<Product> productOptional=productRepository.findById(4L);
        assertTrue(!productOptional.isPresent());
    }

    @Test
    public void findByEmailPositive(){
        Product product=new Product();
        product.setName("Wireless Mouse");
        product.setDescription("Ergonomic 2.4GHz wireless mouse with adjustable DPI.");
        product.setPrice(4599);
        product.setStock(350);

        entityManager.persist(product);
        String name=product.getName();

        List<Product> productList=productRepository.findByName(name);
        assertTrue(productList.iterator().hasNext());
    }

    @Test
    public void findByEmailNegative(){
        List<Product> productList=productRepository.findByName("USB-B Hub");
        assertTrue(!productList.iterator().hasNext());
    }
}

