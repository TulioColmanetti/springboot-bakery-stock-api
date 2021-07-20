package com.projects.dev.tulio.springbootbakerystockapi.service;

import com.projects.dev.tulio.springbootbakerystockapi.dto.ProductDTO;
import com.projects.dev.tulio.springbootbakerystockapi.entity.Product;
import com.projects.dev.tulio.springbootbakerystockapi.exception.ProductAlreadyRegisteredException;
import com.projects.dev.tulio.springbootbakerystockapi.exception.ProductNotFoundException;
import com.projects.dev.tulio.springbootbakerystockapi.mapper.ProductMapper;
import com.projects.dev.tulio.springbootbakerystockapi.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    public ProductDTO createProduct(ProductDTO productDTO) throws ProductAlreadyRegisteredException {
        verifyIfIsAlreadyRegistered(productDTO.getName());
        Product product = productMapper.toModel(productDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    public ProductDTO findByName(String name) throws ProductNotFoundException {
        Product foundProduct = productRepository.findByName(name)
                .orElseThrow(() -> new ProductNotFoundException(name));
        return productMapper.toDTO(foundProduct);
    }

    private void verifyIfIsAlreadyRegistered(String name) throws ProductAlreadyRegisteredException {
        Optional<Product> optSavedProduct = productRepository.findByName(name);
        if (optSavedProduct.isPresent()) {
            throw new ProductAlreadyRegisteredException(name);
        }
    }
}
