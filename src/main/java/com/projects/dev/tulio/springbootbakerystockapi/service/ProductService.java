package com.projects.dev.tulio.springbootbakerystockapi.service;

import com.projects.dev.tulio.springbootbakerystockapi.dto.ProductDTO;
import com.projects.dev.tulio.springbootbakerystockapi.entity.Product;
import com.projects.dev.tulio.springbootbakerystockapi.exception.ProductAlreadyRegisteredException;
import com.projects.dev.tulio.springbootbakerystockapi.exception.ProductNotFoundException;
import com.projects.dev.tulio.springbootbakerystockapi.exception.ProductStockExceededException;
import com.projects.dev.tulio.springbootbakerystockapi.mapper.ProductMapper;
import com.projects.dev.tulio.springbootbakerystockapi.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<ProductDTO> listAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) throws ProductNotFoundException {
        verifyIfExists(id);
        productRepository.deleteById(id);
    }

    private void verifyIfIsAlreadyRegistered(String name) throws ProductAlreadyRegisteredException {
        Optional<Product> optSavedProduct = productRepository.findByName(name);
        if (optSavedProduct.isPresent()) {
            throw new ProductAlreadyRegisteredException(name);
        }
    }

    private Product verifyIfExists(Long id) throws ProductNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public ProductDTO increment(Long id, int quantityToIncrement) throws ProductNotFoundException, ProductStockExceededException {
        Product productToIncrementStock = verifyIfExists(id);
        int quantityAfterIncrement = quantityToIncrement + productToIncrementStock.getQuantity();
        if (quantityAfterIncrement <= productToIncrementStock.getMax()) {
            productToIncrementStock.setQuantity(productToIncrementStock.getQuantity() + quantityToIncrement);
            Product incrementedProductStock = productRepository.save(productToIncrementStock);
            return productMapper.toDTO(incrementedProductStock);
        }
        throw new ProductStockExceededException(id, quantityToIncrement);
    }
}
