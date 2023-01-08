package ru.fomina.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    private String ingredientName;
    private int quantity;
    private String measureUnit;
}
