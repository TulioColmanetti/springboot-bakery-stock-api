package com.projects.dev.tulio.springbootbakerystockapi.service;

import com.projects.dev.tulio.springbootbakerystockapi.dto.ProductDTO;
import com.projects.dev.tulio.springbootbakerystockapi.entity.Product;
import com.projects.dev.tulio.springbootbakerystockapi.mapper.ProductMapper;
import com.projects.dev.tulio.springbootbakerystockapi.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productMapper.toModel(productDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }
}
