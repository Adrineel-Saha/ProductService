package com.cognizant.productservice.services;

import com.cognizant.productservice.dtos.ProductDTO;
import com.cognizant.productservice.entities.Product;
import com.cognizant.productservice.exceptions.ProductNameNotFoundException;
import com.cognizant.productservice.exceptions.ResourceNotFoundException;
import com.cognizant.productservice.repositories.ProductRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestProductServiceImpl {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    private Validator validator;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    public void testGetAllProductsPositiveOneProductFound(){
        try{
            List<Product> listProductMock = mock(List.class);
            when(productRepository.findAll()).thenReturn(listProductMock);

            Product product= mock(Product.class);
            when(listProductMock.stream()).thenReturn(Stream.of(product));

            ProductDTO productDTO = mock(ProductDTO.class);
            when(modelMapper.map(any(Product.class),eq(ProductDTO.class))).thenReturn(productDTO);

            List<ProductDTO> ProductDTOList = productServiceImpl.getAllProducts();
            assertTrue(ProductDTOList.size()==1);
        } catch(Exception ex){
            assertTrue(false);
        }
    }

    @Test
    public void testGetAllProductsPositiveMultipleProductsFound(){
        try{
            List<Product> listProductMock = mock(List.class);
            when(productRepository.findAll()).thenReturn(listProductMock);

            Product product1= mock(Product.class);
            Product product2= mock(Product.class);
            when(listProductMock.stream()).thenReturn(Stream.of(product1,product2));

            ProductDTO productDTO1 = mock(ProductDTO.class);
            ProductDTO productDTO2 = mock(ProductDTO.class);
            when(modelMapper.map(any(Product.class),eq(ProductDTO.class))).thenReturn(productDTO1).thenReturn(productDTO2);

            List<ProductDTO> ProductDTOList = productServiceImpl.getAllProducts();
            assertTrue(ProductDTOList.size()>1);
        } catch(Exception ex){
            assertTrue(false);
        }
    }

    @Test
    public void testGetAllProductsNegativeWhenListIsEmpty(){
        try{
            when(productRepository.findAll()).thenReturn(List.of());

            List<ProductDTO> ProductDTOList = productServiceImpl.getAllProducts();

        } catch(Exception ex){
            assertThatThrownBy(() -> productServiceImpl.getAllProducts())
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Product List is Empty");
        }
    }

    @Test
    public void testGetProductsByNamePositiveOneProductFound(){
        try{
            List<Product> listProductMock = mock(List.class);
            when(productRepository.findByName(anyString())).thenReturn(listProductMock);

            Product product= mock(Product.class);
            when(listProductMock.stream()).thenReturn(Stream.of(product));

            ProductDTO productDTO = mock(ProductDTO.class);
            when(modelMapper.map(any(Product.class),eq(ProductDTO.class))).thenReturn(productDTO);

            List<ProductDTO> ProductDTOList = productServiceImpl.getProductsByName("USB-B Hub");
            assertTrue(ProductDTOList.size()==1);
        } catch(Exception ex){
            assertTrue(false);
        }
    }

    @Test
    public void testGetProductsByNamePositiveMultipleProductsFound(){
        try{
            List<Product> listProductMock = mock(List.class);
            when(productRepository.findByName(anyString())).thenReturn(listProductMock);

            Product product1= mock(Product.class);
            Product product2= mock(Product.class);
            when(listProductMock.stream()).thenReturn(Stream.of(product1,product2));

            ProductDTO productDTO1 = mock(ProductDTO.class);
            ProductDTO productDTO2 = mock(ProductDTO.class);
            when(modelMapper.map(any(Product.class),eq(ProductDTO.class))).thenReturn(productDTO1).thenReturn(productDTO2);

            List<ProductDTO> ProductDTOList = productServiceImpl.getProductsByName("Mechanical Keyboard");
            assertTrue(ProductDTOList.size()>1);
        } catch(Exception ex){
            assertTrue(false);
        }
    }

    @Test
    public void testGetProductsByNameNegativeWhenListIsEmpty(){
        try{
            when(productRepository.findByName(anyString())).thenReturn(List.of());

            List<ProductDTO> ProductDTOList=productServiceImpl.getProductsByName("Portable SSD 1TB");

        } catch(Exception ex){
            assertThat(ex)
                    .isInstanceOf(ProductNameNotFoundException.class)
                    .hasMessage("Product not found with Name: Portable SSD 1TB");
        }
    }

    @Test
    public void testGetProductPositive(){
        try{
            Product product=new Product();
            product.setId(1L);
            product.setName("Mechanical Keyboard");
            product.setDescription("RGB backlit mechanical keyboard with blue switches.");
            product.setPrice(5099);
            product.setStock(200);

            ProductDTO productDTO=new ProductDTO();
            productDTO.setId(1L);
            productDTO.setName("Mechanical Keyboard");
            productDTO.setDescription("RGB backlit mechanical keyboard with blue switches.");
            productDTO.setPrice(5099);
            productDTO.setStock(200);

            when(productRepository.findById(any())).thenReturn(Optional.of(product));
            when(modelMapper.map(any(Product.class),eq(ProductDTO.class))).thenReturn(productDTO);

            ProductDTO actualProductDTO=productServiceImpl.getProduct(1L);
            assertNotNull(actualProductDTO);
        } catch(Exception ex){
            assertTrue(false);
        }
    }

    @Test
    public void testGetProductNegativeWhenProductNotFound(){
        try{
            when(productRepository.findById(any())).thenReturn(Optional.empty());
            ProductDTO actualProductDTO=productServiceImpl.getProduct(1L);
        } catch(Exception ex){
            assertThat(ex)
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessageContaining("Product not found with Id: 1");
        }
    }

    @Test
    public void testDeleteProductPositive(){
        try{
            Product product=new Product();
            product.setId(2L);
            product.setName("Wireless Mouse");
            product.setDescription("Ergonomic 2.4GHz wireless mouse with adjustable DPI.");
            product.setPrice(4099);
            product.setStock(300);

            when(productRepository.findById(any())).thenReturn(Optional.of(product));

            String result=productServiceImpl.deleteProduct(2l);
            assertEquals("Product deleted with Id: 2",result);
        } catch(Exception ex){
            assertTrue(false);
        }
    }

    @Test
    public void testDeleteProductNegativeWhenProductNotFound(){
        try{
            when(productRepository.findById(any())).thenReturn(Optional.empty());
            String result=productServiceImpl.deleteProduct(2l);
        } catch(Exception ex){
            assertThat(ex)
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessageContaining("Product not found with Id: 2");
        }
    }

    @Test
    public void testCreateProductPositive(){
        try{
            ProductDTO productDTO=new ProductDTO();
            productDTO.setName("Portable SSD 1TB");
            productDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
            productDTO.setPrice(3099);
            productDTO.setStock(200);

            Product product=new Product();
            product.setId(1L);
            product.setName("Portable SSD 1TB");
            product.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
            product.setPrice(3099);
            product.setStock(200);

            Product savedproduct=new Product();
            savedproduct.setId(1L);
            savedproduct.setName("Portable SSD 1TB");
            savedproduct.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
            savedproduct.setPrice(3099);
            savedproduct.setStock(200);

            ProductDTO savedproductDTO=new ProductDTO();
            savedproductDTO.setId(1L);
            savedproductDTO.setName("Portable SSD 1TB");
            savedproductDTO.setDescription("High-speed USB 3.2 Gen 2 portable solid-state drive.");
            savedproductDTO.setPrice(3099);
            savedproductDTO.setStock(200);

            when(modelMapper.map(any(ProductDTO.class),eq(Product.class))).thenReturn(product);
            when(productRepository.save(any(Product.class))).thenReturn(savedproduct);
            when(modelMapper.map(any(Product.class),eq(ProductDTO.class))).thenReturn(savedproductDTO);

            ProductDTO actualProductDTO=productServiceImpl.createProduct(productDTO);
            assertNotNull(actualProductDTO);
        } catch(Exception ex){
            assertTrue(false);
        }
    }

    @Test
    public void testCreateProductNegativeWhenProductNameIsBlank(){
        ProductDTO productDTO=new ProductDTO();
        productDTO.setName("");
        productDTO.setDescription("6-in-1 USB-b hub with HDMI, USB 3.0, SD/TF reader.");
        productDTO.setPrice(3599);
        productDTO.setStock(250);

        Set<ConstraintViolation<ProductDTO>> violations = validator.validate(productDTO);

        assertThat(violations)
                .extracting(v -> v.getMessage())
                .anyMatch(msg -> msg.contains("Product Name cannot be blank"));

    }

    @Test
    public void testCreateProductNegativeWhenDescriptionIsBlank(){
        ProductDTO productDTO=new ProductDTO();
        productDTO.setName("Wireless Mouse");
        productDTO.setDescription("");
        productDTO.setPrice(4599);
        productDTO.setStock(350);

        Set<ConstraintViolation<ProductDTO>> violations = validator.validate(productDTO);

        assertThat(violations)
                .extracting(v -> v.getMessage())
                .anyMatch(msg -> msg.contains("Description cannot be blank"));

    }

    @Test
    public void testCreateProductNegativeWhenPriceIsNegative(){
        ProductDTO productDTO=new ProductDTO();
        productDTO.setName("Wireless Mouse");
        productDTO.setDescription("Ergonomic 2.4GHz wireless mouse with adjustable DPI.");
        productDTO.setPrice(-4599);
        productDTO.setStock(350);

        Set<ConstraintViolation<ProductDTO>> violations = validator.validate(productDTO);

        assertThat(violations)
                .extracting(v -> v.getMessage())
                .anyMatch(msg -> msg.contains("Price should be positive"));

    }

    @Test
    public void testCreateProductNegativeWhenPriceIsZero(){
        ProductDTO productDTO=new ProductDTO();
        productDTO.setName("Wireless Mouse");
        productDTO.setDescription("Ergonomic 2.4GHz wireless mouse with adjustable DPI.");
        productDTO.setPrice(0);
        productDTO.setStock(350);

        Set<ConstraintViolation<ProductDTO>> violations = validator.validate(productDTO);

        assertThat(violations)
                .extracting(v -> v.getMessage())
                .anyMatch(msg -> msg.contains("Price should be positive"));

    }

    @Test
    public void testCreateProductNegativeWhenStockIsNegative(){
        ProductDTO productDTO=new ProductDTO();
        productDTO.setName("Wireless Mouse");
        productDTO.setDescription("Ergonomic 2.4GHz wireless mouse with adjustable DPI.");
        productDTO.setPrice(4599);
        productDTO.setStock(-350);

        Set<ConstraintViolation<ProductDTO>> violations = validator.validate(productDTO);

        assertThat(violations)
                .extracting(v -> v.getMessage())
                .anyMatch(msg -> msg.contains("Stock cannot less then 0"));

    }

}
