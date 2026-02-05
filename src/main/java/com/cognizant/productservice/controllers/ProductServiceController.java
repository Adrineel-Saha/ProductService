package com.cognizant.productservice.controllers;

import com.cognizant.productservice.dtos.ProductDTO;
import com.cognizant.productservice.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@Tag(
        name="CRUD REST APIs for Product Service",
        description="CRUD REST APIs - Create Product, Get Product, Update Product, Delete Product"
)
public class ProductServiceController {
    @Autowired
    private ProductService productService;

    private static final Logger log = LoggerFactory.getLogger(ProductServiceController.class);

    @PostMapping
    @Operation(
            summary="Create Product REST API",
            description="Used to save a product to the database"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description="Product Created"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description="Bad Request"
            )
    })
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO){
        log.info("Adding new Product: " + productDTO);
        ProductDTO responseProductDTO =productService.createProduct(productDTO);
        log.info("Created Product: " + responseProductDTO);

        if(responseProductDTO!=null){
            return new ResponseEntity<>(responseProductDTO, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    @Operation(
            summary="Get All Products REST API",
            description="Used to get all products from the database"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description="Products Found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description="Bad Request"
            )
    })
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        log.info("Getting All Products");
        List<ProductDTO> responseProductDTOList =productService.getAllProducts();
        log.info("Products list: " + responseProductDTOList);
        if(responseProductDTOList!=null && !responseProductDTOList.isEmpty()){
            return new ResponseEntity<>(responseProductDTOList, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    @Operation(
            summary="Get Product By Id REST API",
            description="Used to get a single product from the database"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description="Product Found"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description="Product Not Found"
            )
    })
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Long id){
        log.info("Getting Product with Id: " + id);
        ProductDTO responseProductDTO =productService.getProduct(id);
        log.info("Found Product: " + responseProductDTO);
        if (responseProductDTO != null) {
            return new ResponseEntity<>(responseProductDTO, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{name}")
    @Operation(
            summary="Get Products By Name REST API",
            description="Used to get all products by name from the database"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description="Products Found"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description="Products Not Found"
            )
    })
    public ResponseEntity<List<ProductDTO>> getProductsByName(@PathVariable("name") String name){
        log.info("Getting All Products By Name");
        List<ProductDTO> responseProductDTOList =productService.getProductsByName(name);
        log.info("Products list: " + responseProductDTOList);
        if(responseProductDTOList!=null && !responseProductDTOList.isEmpty()){
            return new ResponseEntity<>(responseProductDTOList, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    @Operation(
            summary="Update User REST API",
            description="Used to update a user in the database"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "202",
                    description="User Updated"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description="User Not Found"
            )
    })
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") Long id,@Valid @RequestBody ProductDTO productDTO){
        log.info("Updating Product with Id: " + id + " and details: " + productDTO);
        ProductDTO responseProductDTO =productService.updateProduct(id, productDTO);
        log.info("Updated User: " + responseProductDTO);
        if(responseProductDTO != null){
            return new ResponseEntity<>(responseProductDTO, HttpStatus.ACCEPTED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("{id}")
    @Operation(
            summary="Delete Product REST API",
            description="Used to delete a product from the database"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description="Product Deleted"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description="Product Not Found"
            ),
    })
    public ResponseEntity<String>  deleteUser(@PathVariable("id") Long id){
        log.info("Deleting Product with Id: " + id);
        String result=productService.deleteProduct(id);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
