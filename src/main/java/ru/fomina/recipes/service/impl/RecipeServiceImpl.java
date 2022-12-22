package ru.fomina.recipes.service.impl;

import org.springframework.stereotype.Service;
import ru.fomina.recipes.model.ElementNotFoundException;
import ru.fomina.recipes.model.Recipe;
import ru.fomina.recipes.service.RecipeService;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static Integer recipeId = 0;

    private static Map<Integer, Recipe> recipeMap = new HashMap<>();

    @Override
    public void addRecipe(Recipe recipe) {
        Recipe recipeMapOrDefault = recipeMap.getOrDefault(recipeId, recipe);
        recipeMap.put(recipeId++, recipe);
    }

    @Override
    public Recipe getRecipe(Integer recipeId) {
        if (recipeMap.containsKey(recipeId)) {
            return getRecipe(recipeId);
        } else {
            throw new ElementNotFoundException("Нет рецепта с таким номером!");
        }
    }
}
