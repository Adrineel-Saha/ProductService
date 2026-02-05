package com.cognizant.productservice.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ProductDTO {
    private Long id;

    @NotBlank(message="Product Name cannot be blank")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String description;

    @Positive(message="Price should be positive")
    private double price;

    @Min(value = 0, message="Stock cannot less then 0")
    private int stock;

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "[ ProductId: " + id + " , ProductName: " + name + " , Description: " + description + " , Price: " + price + " , Stock: " + stock + " ]";
    }
}
