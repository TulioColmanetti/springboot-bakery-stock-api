package com.projects.dev.tulio.springbootbakerystockapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductStockExceededException extends Exception {

    public ProductStockExceededException(Long id, int quantityToIncrement) {
        super(String.format("Products with %s ID to increment informed exceeds the max stock capacity: %s", id, quantityToIncrement));
    }
}
