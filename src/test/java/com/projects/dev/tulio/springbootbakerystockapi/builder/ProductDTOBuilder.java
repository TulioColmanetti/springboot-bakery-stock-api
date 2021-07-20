package com.projects.dev.tulio.springbootbakerystockapi.builder;

import com.projects.dev.tulio.springbootbakerystockapi.dto.ProductDTO;
import com.projects.dev.tulio.springbootbakerystockapi.enums.ProductCategory;
import com.projects.dev.tulio.springbootbakerystockapi.enums.ProductSize;
import lombok.Builder;

@Builder
public class ProductDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String name = "Cookie de Nutella";

    @Builder.Default
    private String description = "Cookie recheado com nutella";

    @Builder.Default
    private ProductCategory category = ProductCategory.DOCES;

    @Builder.Default
    private ProductSize size = ProductSize.NORMAL;

    @Builder.Default
    private double cost = 10d;

    @Builder.Default
    private int max = 40;

    @Builder.Default
    private int quantity = 15;

    public ProductDTO toProductDTO(){
        return new ProductDTO(id,name,description,category,size,cost,max,quantity);
    }
}
