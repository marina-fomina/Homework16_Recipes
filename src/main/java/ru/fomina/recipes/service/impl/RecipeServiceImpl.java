package ru.fomina.recipes.service.impl;

import org.springframework.stereotype.Service;
import ru.fomina.recipes.model.ElementNotFoundException;
import ru.fomina.recipes.model.Recipe;
import ru.fomina.recipes.service.RecipeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {

    private Integer recipeId = 0;
    private final Map<Integer, Recipe> recipeMap = new HashMap<>();

    @Override
    public Recipe addRecipe(Recipe recipe) {
        Recipe recipeMapOrDefault = recipeMap.getOrDefault(recipeId, recipe);
        recipeMap.put(recipeId++, recipe);
        return recipe;
    }

    @Override
    public Recipe getRecipe(Integer recipeId) {
        if (recipeMap.containsKey(recipeId)) {
            return recipeMap.get(recipeId);
        } else {
            throw new ElementNotFoundException("Нет рецепта с таким номером!");
        }
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return new ArrayList<>(recipeMap.values());
    }

    @Override
    public Recipe editRecipe(Integer recipeId, Recipe recipe) {
        if (recipeMap.containsKey(recipeId)) {
            recipeMap.put(recipeId, recipe);
            return recipe;
        } else {
            throw new ElementNotFoundException("Нет рецепта с таким номером!");
        }
    }

    @Override
    public boolean deleteRecipe(Integer recipeId) {
        if (recipeMap.containsKey(recipeId)) {
            recipeMap.remove(recipeId);
            return true;
        } else {
            return false;
        }
    }
}
