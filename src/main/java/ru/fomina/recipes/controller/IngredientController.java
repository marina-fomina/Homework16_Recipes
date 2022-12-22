package ru.fomina.recipes.controller;

import org.springframework.web.bind.annotation.*;
import ru.fomina.recipes.model.Ingredient;
import ru.fomina.recipes.service.IngredientService;


@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping("/add")
    public void addIngredient (@RequestParam Ingredient ingredient) {
        ingredientService.addIngredient(ingredient);
    }

    @GetMapping("/get")
    public void getIngredient(@RequestParam Integer ingredientId) {
        ingredientService.getIngredient(ingredientId);
    }
}
