package com.cognizant.productservice.exceptions;

public class ProductNameNotFoundException extends RuntimeException {
    public ProductNameNotFoundException(String message) {
        super(message);
    }
}
