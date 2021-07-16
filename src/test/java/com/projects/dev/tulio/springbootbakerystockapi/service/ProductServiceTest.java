package com.projects.dev.tulio.springbootbakerystockapi.service;

import com.projects.dev.tulio.springbootbakerystockapi.builder.ProductBuilder;
import com.projects.dev.tulio.springbootbakerystockapi.entity.Product;
import com.projects.dev.tulio.springbootbakerystockapi.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Test
    void whenProductInformedThenItShouldCreated(){
//        given
        Product expectedSavedProduct = ProductBuilder.builder().build().createProduct();

//        when
        when(productRepository.save(expectedSavedProduct)).thenReturn(expectedSavedProduct);

//        then
        Product savedProduct = productService.createProduct(expectedSavedProduct);

    }

}