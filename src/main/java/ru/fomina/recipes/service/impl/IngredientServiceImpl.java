package ru.fomina.recipes.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.fomina.recipes.model.ElementNotFoundException;
import ru.fomina.recipes.model.Ingredient;
import ru.fomina.recipes.service.FilesService;
import ru.fomina.recipes.service.IngredientService;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final FilesService filesService;

    private Integer ingredientId = 0;
    private Map<Integer, Ingredient> ingredientMap = new LinkedHashMap<>();

    public IngredientServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    @PostConstruct
    private void init() {
        readIngredientFromFile();
    }


    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        Ingredient ingredientMapOrDefault = ingredientMap.getOrDefault(ingredientId, ingredient);
        ingredientMap.put(ingredientId++, ingredient);
        saveIngredientToFile();
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
            saveIngredientToFile();
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

    private void saveIngredientToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientMap);
            filesService.saveIngredientToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readIngredientFromFile() {
        try {
            String json = filesService.readIngredientFromFile();
            ingredientMap = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
