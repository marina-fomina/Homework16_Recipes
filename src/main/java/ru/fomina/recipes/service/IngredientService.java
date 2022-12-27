package ru.fomina.recipes.service;

import ru.fomina.recipes.model.Ingredient;
import java.util.List;

public interface IngredientService {
    Ingredient addIngredient(Ingredient ingredient);
    Ingredient getIngredient(Integer ingredientId);

    List<Ingredient> getAllIngredients();

    Ingredient editIngredient(Integer ingredientId, Ingredient ingredient);

    boolean deleteIngredient(Integer ingredientId);
}
