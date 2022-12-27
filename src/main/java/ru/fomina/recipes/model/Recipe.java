package ru.fomina.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Objects;

@Data
@AllArgsConstructor
public class Recipe {
    private final String recipeName;
    private int cookingTime;
    private ArrayList <Ingredient> ingredients = new ArrayList<>();
    private LinkedHashSet <String> steps = new LinkedHashSet<>();
}
