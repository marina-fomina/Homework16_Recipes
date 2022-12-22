package ru.fomina.recipes.service.impl;

import org.springframework.stereotype.Service;
import ru.fomina.recipes.model.ElementNotFoundException;
import ru.fomina.recipes.model.Ingredient;
import ru.fomina.recipes.service.IngredientService;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {

    private static Integer ingredientId = 0;
    private static Map<Integer, Ingredient> ingredientMap = new HashMap<>();


    @Override
    public void addIngredient(Ingredient ingredient) {
        Ingredient ingredientMapOrDefault = ingredientMap.getOrDefault(ingredientId, ingredient);
        ingredientMap.put(ingredientId++, ingredient);
    }

    @Override
    public void getIngredient(Integer ingredientId) {
        if (ingredientMap.containsKey(ingredientId)) {
            getIngredient(ingredientId);
        } else {
            throw new ElementNotFoundException("Нет ингредиента с таким номером!");
        }
    }
}
