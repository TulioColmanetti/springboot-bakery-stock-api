package com.projects.dev.tulio.springbootbakerystockapi.controller;

import com.projects.dev.tulio.springbootbakerystockapi.builder.ProductDTOBuilder;
import com.projects.dev.tulio.springbootbakerystockapi.dto.ProductDTO;
import com.projects.dev.tulio.springbootbakerystockapi.exception.ProductNotFoundException;
import com.projects.dev.tulio.springbootbakerystockapi.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;

import static com.projects.dev.tulio.springbootbakerystockapi.utils.JsonConversionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    private static final String PRODUCTS_API_PATH = "/api/v1/products";
    private static final long INVALID_PRODUCT_ID = 2l;

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledThenAProductIsCreated() throws Exception {
//        given
        ProductDTO productDTO = ProductDTOBuilder.builder().build().toProductDTO();

//        when
        when(productService.createProduct(productDTO)).thenReturn(productDTO);

//        then
        mockMvc.perform(post(PRODUCTS_API_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(productDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(productDTO.getName())))
                .andExpect(jsonPath("$.category", is(productDTO.getCategory().toString())))
                .andExpect(jsonPath("$.size", is(productDTO.getSize().toString())));
    }

    @Test
    void whenPOSTIsCalledWithoutRequiredFieldThenAnErrorIsReturned() throws Exception {
        // given
        ProductDTO productDTO = ProductDTOBuilder.builder().build().toProductDTO();
        productDTO.setCategory(null);

        // then
        mockMvc.perform(post(PRODUCTS_API_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(productDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenGETIsCalledWithValidNameThenOkStatusIsReturned() throws Exception {
        // given
        ProductDTO productDTO = ProductDTOBuilder.builder().build().toProductDTO();

        //when
        when(productService.findByName(productDTO.getName())).thenReturn(productDTO);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(PRODUCTS_API_PATH + "/" + productDTO.getName())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(productDTO.getName())))
                .andExpect(jsonPath("$.category", is(productDTO.getCategory().toString())))
                .andExpect(jsonPath("$.size", is(productDTO.getSize().toString())));
    }

    @Test
    void whenGETIsCalledWithoutRegisteredNameThenNotFoundStatusIsReturned() throws Exception {
        // given
        ProductDTO productDTO = ProductDTOBuilder.builder().build().toProductDTO();

        //when
        when(productService.findByName(productDTO.getName())).thenThrow(ProductNotFoundException.class);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(PRODUCTS_API_PATH + "/" + productDTO.getName())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenGETListWithProductsIsCalledThenOkStatusIsReturned() throws Exception {
        // given
        ProductDTO productDTO = ProductDTOBuilder.builder().build().toProductDTO();

        //when
        when(productService.listAll()).thenReturn(Collections.singletonList(productDTO));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(PRODUCTS_API_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(productDTO.getName())))
                .andExpect(jsonPath("$[0].category", is(productDTO.getCategory().toString())))
                .andExpect(jsonPath("$[0].size", is(productDTO.getSize().toString())));
    }

    @Test
    void whenGETListWithoutProductsIsCalledThenOkStatusIsReturned() throws Exception {
        // given
        ProductDTO productDTO = ProductDTOBuilder.builder().build().toProductDTO();

        //when
        when(productService.listAll()).thenReturn(Collections.singletonList(productDTO));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(PRODUCTS_API_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenDELETEIsCalledWithValidIdThenNoContentStatusIsReturned() throws Exception {
        // given
        ProductDTO productDTO = ProductDTOBuilder.builder().build().toProductDTO();

        //when
        doNothing().when(productService).deleteById(productDTO.getId());

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete(PRODUCTS_API_PATH + "/" + productDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void whenDELETEIsCalledWithInvalidIdThenNotFoundStatusIsReturned() throws Exception {
        //when
        doThrow(ProductNotFoundException.class).when(productService).deleteById(INVALID_PRODUCT_ID);

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete(PRODUCTS_API_PATH + "/" + INVALID_PRODUCT_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}