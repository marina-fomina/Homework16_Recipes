package ru.fomina.recipes.controller;

import org.springframework.web.bind.annotation.*;
import ru.fomina.recipes.model.Recipe;
import ru.fomina.recipes.service.RecipeService;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/add")
    public void addRecipe(@RequestParam Recipe recipe) {
        recipeService.addRecipe(recipe);
    }

    @GetMapping("/get")
    public Recipe getRecipe(@RequestParam Integer recipeId) {
        return recipeService.getRecipe(recipeId);
    }
}
