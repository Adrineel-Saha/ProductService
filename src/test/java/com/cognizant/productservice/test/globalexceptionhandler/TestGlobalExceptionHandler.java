package com.cognizant.productservice.test.globalexceptionhandler;

import com.cognizant.productservice.exceptions.ProductNameNotFoundException;
import com.cognizant.productservice.exceptions.ResourceNotFoundException;
import com.cognizant.productservice.globalexceptionhandler.GlobalExceptionHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestGlobalExceptionHandler {

    @Mock
    private ResourceNotFoundException resourceNotFoundException;

    @Mock
    private ProductNameNotFoundException productNameNotFoundException;

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void handleResourceNotFoundException_returns404WithMessage() {
        when(resourceNotFoundException.getMessage()).thenReturn("Product not found");

        ResponseEntity<String> response = globalExceptionHandler.handleResourceNotFoundException(resourceNotFoundException);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Product not found", response.getBody());
    }

    @Test
    void handleProductNameNotFoundException_returns404WithMessage() {
        when(productNameNotFoundException.getMessage()).thenReturn("Product name not found");

        ResponseEntity<String> response = globalExceptionHandler.handleResourceNotFoundException(productNameNotFoundException);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Product name not found", response.getBody());
    }

    @Test
    void handleMethodArgumentNotValidException_returns400WithJoinedMessages() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);

        ObjectError error1 = new ObjectError("name", "Product Name cannot be blank");
        ObjectError error2 = new ObjectError("description", "Description cannot be blank");
        when(bindingResult.getAllErrors()).thenReturn(List.of(error1, error2));
        when(ex.getBindingResult()).thenReturn(bindingResult);

        ResponseEntity<String> response = globalExceptionHandler.handleConstraintViolation(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Product Name cannot be blank, Description cannot be blank", response.getBody());
    }

    @Test
    void handleMethodArgumentNotValidException_singleError_returnsMessage() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);

        ObjectError error = new ObjectError("price", "Price should be positive");
        when(bindingResult.getAllErrors()).thenReturn(List.of(error));
        when(ex.getBindingResult()).thenReturn(bindingResult);

        ResponseEntity<String> response = globalExceptionHandler.handleConstraintViolation(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Price should be positive", response.getBody());
    }

    @Test
    void handleGenericException_returns400WithMessage() {
        Exception ex = mock(Exception.class);
        when(ex.getMessage()).thenReturn("Unexpected error");

        ResponseEntity<String> response = globalExceptionHandler.exceptionHandler(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Unexpected error", response.getBody());
    }

    @Test
    void handleGenericException_nullMessage_returnsNullBody() {
        Exception ex = mock(Exception.class);
        when(ex.getMessage()).thenReturn(null);

        ResponseEntity<String> response = globalExceptionHandler.exceptionHandler(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(null, response.getBody());
    }
}
