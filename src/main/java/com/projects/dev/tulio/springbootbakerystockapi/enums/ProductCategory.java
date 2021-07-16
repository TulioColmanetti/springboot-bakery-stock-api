package com.projects.dev.tulio.springbootbakerystockapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductCategory {

    PAES("Paes"),
    DOCES("Doces");

    private final String description;
}
