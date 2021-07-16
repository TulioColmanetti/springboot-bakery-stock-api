package com.projects.dev.tulio.springbootbakerystockapi.entity;

import com.projects.dev.tulio.springbootbakerystockapi.enums.ProductCategory;
import com.projects.dev.tulio.springbootbakerystockapi.enums.ProductSize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductCategory category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductSize size;

    @Column(nullable = false)
    private double cost;

    @Column(nullable = false)
    private int max;

    @Column(nullable = false)
    private int quantity;
}
