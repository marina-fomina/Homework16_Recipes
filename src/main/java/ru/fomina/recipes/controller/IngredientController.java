package ru.fomina.recipes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fomina.recipes.model.Ingredient;
import ru.fomina.recipes.service.IngredientService;

import java.util.List;



@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping("/add")
    public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient) {
        ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok(ingredient);
    }

    @GetMapping("/{ingredientId}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable("ingredientId") Integer ingredientId) {
        Ingredient ingredient = ingredientService.getIngredient(ingredientId);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        List<Ingredient> allIngredients = ingredientService.getAllIngredients();
        if (allIngredients.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allIngredients);
    }

    @PutMapping("/{ingredientId}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable("ingredientId") Integer ingredientId, @RequestBody Ingredient ingredient) {
        Ingredient newIngredient = ingredientService.editIngredient(ingredientId, ingredient);
        if (newIngredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(newIngredient);
    }

    @DeleteMapping("/{ingredientId}")
    public ResponseEntity<String > deleteIngredient(@PathVariable("ingredientId") Integer ingredientId) {
        if (ingredientService.deleteIngredient(ingredientId)) {
            return ResponseEntity.ok("Ингредиент номер " + ingredientId + " удалён.");
        }
        return ResponseEntity.notFound().build();
    }
}
