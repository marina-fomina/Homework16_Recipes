package ru.fomina.recipes.service.impl;

import org.springframework.stereotype.Service;
import ru.fomina.recipes.model.ElementNotFoundException;
import ru.fomina.recipes.model.Ingredient;
import ru.fomina.recipes.service.IngredientService;

import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService {

    private Integer ingredientId = 0;
    private final Map<Integer, Ingredient> ingredientMap = new HashMap<>();


    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        Ingredient ingredientMapOrDefault = ingredientMap.getOrDefault(ingredientId, ingredient);
        ingredientMap.put(ingredientId++, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient getIngredient(Integer ingredientId) {
        if (ingredientMap.containsKey(ingredientId)) {
            return ingredientMap.get(ingredientId);
        } else {
            throw new ElementNotFoundException("Нет ингредиента с таким номером!");
        }
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return new ArrayList<>(ingredientMap.values());
    }

    @Override
    public Ingredient editIngredient(Integer ingredientId, Ingredient ingredient) {
        if (ingredientMap.containsKey(ingredientId)) {
            ingredientMap.put(ingredientId, ingredient);
            return ingredient;
        } else {
            throw new ElementNotFoundException("Нет ингредиента с таким номером!");
        }
    }

    @Override
    public boolean deleteIngredient(Integer ingredientId) {
        if (ingredientMap.containsKey(ingredientId)) {
            ingredientMap.remove(ingredientId);
            return true;
        } else {
            return false;
        }
    }
}
