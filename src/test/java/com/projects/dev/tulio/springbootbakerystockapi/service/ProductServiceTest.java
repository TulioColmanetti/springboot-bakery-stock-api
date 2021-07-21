package com.projects.dev.tulio.springbootbakerystockapi.service;

import com.projects.dev.tulio.springbootbakerystockapi.builder.ProductDTOBuilder;
import com.projects.dev.tulio.springbootbakerystockapi.dto.ProductDTO;
import com.projects.dev.tulio.springbootbakerystockapi.entity.Product;
import com.projects.dev.tulio.springbootbakerystockapi.exception.ProductAlreadyRegisteredException;
import com.projects.dev.tulio.springbootbakerystockapi.exception.ProductNotFoundException;
import com.projects.dev.tulio.springbootbakerystockapi.mapper.ProductMapper;
import com.projects.dev.tulio.springbootbakerystockapi.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.empty;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    private ProductMapper productMapper = ProductMapper.INSTANCE;

    @InjectMocks
    ProductService productService;

    @Test
    void whenProductInformedThenItShouldBeCreated() throws ProductAlreadyRegisteredException {
//        given
        ProductDTO expectedProductDTO = ProductDTOBuilder.builder().build().toProductDTO();
        Product expectedSavedProduct = productMapper.toModel(expectedProductDTO);

//        when
        when(productRepository.findByName(expectedProductDTO.getName())).thenReturn(Optional.empty());
        when(productRepository.save(expectedSavedProduct)).thenReturn(expectedSavedProduct);

//        then
        ProductDTO createdProductDTO = productService.createProduct(expectedProductDTO);

        assertThat(createdProductDTO.getId(), is(equalTo(expectedProductDTO.getId())));
        assertThat(createdProductDTO.getName(), is(equalTo(expectedProductDTO.getName())));
        assertThat(createdProductDTO.getQuantity(), is(equalTo(expectedProductDTO.getQuantity())));
    }

    @Test
    void whenAlreadyRegisteredProductInformedThenAnExceptionShouldBeThrown() {
        // given
        ProductDTO expectedProductDTO = ProductDTOBuilder.builder().build().toProductDTO();
        Product duplicatedProduct = productMapper.toModel(expectedProductDTO);

        // when
        when(productRepository.findByName(expectedProductDTO.getName())).thenReturn(Optional.of(duplicatedProduct));

        // then
        assertThrows(ProductAlreadyRegisteredException.class, () -> productService.createProduct(expectedProductDTO));
    }

    @Test
    void whenValidProductNameIsGivenThenReturnAProduct() throws ProductNotFoundException {
//         given
        ProductDTO expectedFoundProductDTO = ProductDTOBuilder.builder().build().toProductDTO();
        Product expectedFoundProduct = productMapper.toModel(expectedFoundProductDTO);

//         when
        when(productRepository.findByName(expectedFoundProduct.getName())).thenReturn(Optional.of(expectedFoundProduct));

//         then
        ProductDTO foundProductDTO = productService.findByName(expectedFoundProductDTO.getName());

        assertThat(foundProductDTO, is(equalTo(expectedFoundProductDTO)));
    }

    @Test
    void whenNotRegisteredProductNameIsGivenThenThrowAnException() {
        // given
        ProductDTO expectedFoundProductDTO = ProductDTOBuilder.builder().build().toProductDTO();

        // when
        when(productRepository.findByName(expectedFoundProductDTO.getName())).thenReturn(Optional.empty());

        // then
        assertThrows(ProductNotFoundException.class, () -> productService.findByName(expectedFoundProductDTO.getName()));
    }

    @Test
    void whenListProductIsCalledThenReturnAListOfProducts() {
        // given
        ProductDTO expectedFoundProductDTO = ProductDTOBuilder.builder().build().toProductDTO();
        Product expectedFoundProduct = productMapper.toModel(expectedFoundProductDTO);

        //when
        when(productRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundProduct));

        //then
        List<ProductDTO> foundListProductsDTO = productService.listAll();

        assertThat(foundListProductsDTO, is(not(empty())));
        assertThat(foundListProductsDTO.get(0), is(equalTo(expectedFoundProductDTO)));
    }
}