package com.cognizant.productservice.services;

import com.cognizant.productservice.dtos.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);
    List<ProductDTO> getAllProducts();
    ProductDTO  getProduct(Long id);
    List<ProductDTO> getProductsByName(String name);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    String deleteProduct(Long id);
}
