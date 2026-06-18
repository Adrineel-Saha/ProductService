package com.cognizant.productservice.services;

import com.cognizant.productservice.dtos.ProductDTO;
import com.cognizant.productservice.entities.Product;
import com.cognizant.productservice.exceptions.ProductNameNotFoundException;
import com.cognizant.productservice.exceptions.ResourceNotFoundException;
import com.cognizant.productservice.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private KafkaTemplate<String, ProductDTO> kafkaTemplate;
    @Value("${app.kafka.productproducer.topic}")
    private String topic;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product=modelMapper.map(productDTO,Product.class);
        Product savedProduct=productRepository.save(product);
        ProductDTO savedProductDTO = modelMapper.map(savedProduct,ProductDTO.class);

        kafkaTemplate.send(topic, savedProductDTO);
        log.info("Published ProductDTO to {}: {}", topic, savedProductDTO);

        return savedProductDTO;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> productList=productRepository.findAll();
        List<ProductDTO> productDTOList=productList.stream()
                .map(product->modelMapper.map(product,ProductDTO.class)).toList();

        if(productDTOList.isEmpty()){
            throw new RuntimeException("Product List is Empty");
        }

        return productDTOList;
    }

    @Override
    public ProductDTO getProduct(Long id) {
        Product product=productRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Product not found with Id: "+ id)
        );

        ProductDTO productDTO=modelMapper.map(product,ProductDTO.class);

        kafkaTemplate.send(topic, productDTO);
        log.info("Published ProductDTO to {}: {}", topic, productDTO);

        return productDTO;
    }

    @Override
    public List<ProductDTO> getProductsByName(String name) {
        List<Product> productList=productRepository.findByName(name);
        List<ProductDTO> productDTOList=productList.stream()
                .map(product->modelMapper.map(product,ProductDTO.class)).toList();

        if(productDTOList.isEmpty()){
            throw new ProductNameNotFoundException("Product not found with Name: " + name);
        }

        return productDTOList;
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product=productRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Product not found with Id: " + id)
        );

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());

        Product updatedProduct=productRepository.save(product);
        ProductDTO updatedProductDTO=modelMapper.map(updatedProduct, ProductDTO.class);

        kafkaTemplate.send(topic, updatedProductDTO);
        log.info("Published ProductDTO to {}: {}", topic, updatedProductDTO);

        return updatedProductDTO;
    }

    @Override
    public String deleteProduct(Long id) {
        Product product=productRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Product not found with Id: " + id)
        );

        log.info("Deleted Product: " + product);

        productRepository.delete(product);
        return "Product deleted with Id: " + id;
    }
}
