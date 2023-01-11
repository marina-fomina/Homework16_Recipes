package ru.fomina.recipes.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.fomina.recipes.model.ElementNotFoundException;
import ru.fomina.recipes.model.Ingredient;
import ru.fomina.recipes.model.Recipe;
import ru.fomina.recipes.service.FilesService;
import ru.fomina.recipes.service.RecipeService;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final FilesService filesService;

    private Integer recipeId = 0;
    private LinkedHashMap<Integer, Recipe> recipeMap = new LinkedHashMap<>();

    public RecipeServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    @PostConstruct
    private void init() {
        try {
            readRecipeFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        Recipe recipeMapOrDefault = recipeMap.getOrDefault(recipeId, recipe);
        recipeMap.put(recipeId++, recipe);
        saveRecipeToFile();
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
            saveRecipeToFile();
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

    private void saveRecipeToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            filesService.saveRecipeToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void readRecipeFromFile() {
        try {
            String json = filesService.readRecipeFromFile();
            recipeMap = new ObjectMapper().readValue(json, new TypeReference<LinkedHashMap<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
