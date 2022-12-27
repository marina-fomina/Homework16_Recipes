package ru.fomina.recipes.service;

import ru.fomina.recipes.model.Recipe;
import java.util.List;

public interface RecipeService {
    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(Integer recipeId);

    List<Recipe> getAllRecipes();

    Recipe editRecipe(Integer recipeId, Recipe recipe);

    boolean deleteRecipe(Integer recipeId);
}
