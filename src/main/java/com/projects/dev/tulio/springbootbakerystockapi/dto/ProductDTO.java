package com.projects.dev.tulio.springbootbakerystockapi.dto;

import com.projects.dev.tulio.springbootbakerystockapi.enums.ProductCategory;
import com.projects.dev.tulio.springbootbakerystockapi.enums.ProductSize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    @NotNull
    @Size(min = 1, max = 200)
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ProductCategory category;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ProductSize size;

    @NotNull
    @DecimalMin(value = "0.1")
    @DecimalMax(value = "10000")
    private double cost;

    @NotNull
    @Max(800)
    private int max;

    @NotNull
    @Max(50)
    private int quantity;
}
