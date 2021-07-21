package com.projects.dev.tulio.springbootbakerystockapi.controller;

import com.projects.dev.tulio.springbootbakerystockapi.dto.ProductDTO;
import com.projects.dev.tulio.springbootbakerystockapi.exception.ProductAlreadyRegisteredException;
import com.projects.dev.tulio.springbootbakerystockapi.exception.ProductNotFoundException;
import com.projects.dev.tulio.springbootbakerystockapi.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProduct(@RequestBody @Valid ProductDTO productDTO) throws ProductAlreadyRegisteredException {
        return productService.createProduct(productDTO);
    }

    @GetMapping("/{name}")
    public ProductDTO findByName(@PathVariable String name) throws ProductNotFoundException {
        return productService.findByName(name);
    }

    @GetMapping
    public List<ProductDTO> listProducts() {
        return productService.listAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws ProductNotFoundException {
        productService.deleteById(id);
    }
}
