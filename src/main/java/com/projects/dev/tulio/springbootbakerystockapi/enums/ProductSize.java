package com.projects.dev.tulio.springbootbakerystockapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductSize {

    PEQUENO("Pequeno"),
    NORMAL("Normal"),
    GRANDE("Grande");

    private final String description;
}