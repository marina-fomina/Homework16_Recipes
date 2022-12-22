package ru.fomina.recipes.service;

import ru.fomina.recipes.model.Recipe;

public interface RecipeService {
    void addRecipe(Recipe recipe);

    Recipe getRecipe(Integer recipeId);
}
