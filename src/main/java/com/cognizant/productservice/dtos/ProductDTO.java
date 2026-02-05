package com.cognizant.productservice.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Schema(
        description = "ProductDTO model information"
)
public class ProductDTO {
    @Schema(
            description = "Product Id"
    )
    private Long id;

    @NotBlank(message="Product Name cannot be blank")
    @Schema(
            description = "Product Name"
    )
    private String name;

    @Schema(
            description = "Description"
    )
    private String description;

    @Positive(message="Price should be positive")
    @Schema(
            description = "Price"
    )
    private double price;

    @Min(value = 0, message="Stock cannot less then 0")
    @Schema(
            description = "Stock"
    )
    private int stock;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "[ ProductId: " + id + " , ProductName: " + name + " , Description: " + description + " , Price: " + price + " , Stock: " + stock + " ]";
    }
}
