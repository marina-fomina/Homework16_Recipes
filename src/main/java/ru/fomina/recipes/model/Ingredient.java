package ru.fomina.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Ingredient {
    private final String ingredientName;
    private int quantity;
    private String measureUnit;
}
