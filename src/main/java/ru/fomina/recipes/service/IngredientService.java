package ru.fomina.recipes.service;

import ru.fomina.recipes.model.Ingredient;

public interface IngredientService {
    void addIngredient(Ingredient ingredient);
    void getIngredient(Integer ingredientId);
}
