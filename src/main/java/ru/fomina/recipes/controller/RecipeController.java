package ru.fomina.recipes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fomina.recipes.model.Recipe;
import ru.fomina.recipes.service.RecipeService;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/add")
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        recipeService.addRecipe(recipe);
        return ResponseEntity.ok().body(recipe);
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable("recipeId") Integer recipeId) {
        Recipe recipe = recipeService.getRecipe(recipeId);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        List<Recipe> allRecipes = recipeService.getAllRecipes();
        if (allRecipes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allRecipes);
    }

    @PutMapping("/{recipeId}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable("recipeId") Integer recipeId, @RequestBody Recipe recipe) {
        Recipe newRecipe = recipeService.editRecipe(recipeId, recipe);
        if (newRecipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(newRecipe);
    }

    @DeleteMapping("/{recipeId}")
    public ResponseEntity<String> deleteRecipe(@PathVariable("recipeId") Integer recipeId) {
        if (recipeService.deleteRecipe(recipeId)) {
            return ResponseEntity.ok("Рецепт номер " + recipeId + " удалён.");
        }
        return ResponseEntity.notFound().build();
    }
}
