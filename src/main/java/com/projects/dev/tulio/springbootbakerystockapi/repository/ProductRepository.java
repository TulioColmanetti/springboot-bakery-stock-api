package com.projects.dev.tulio.springbootbakerystockapi.repository;

import com.projects.dev.tulio.springbootbakerystockapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);
}
