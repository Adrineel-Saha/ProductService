package com.cognizant.productservice.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Schema(
        description = "ProductDTO model information"
)
@Data
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

    @NotBlank(message="Description cannot be blank")
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

}
