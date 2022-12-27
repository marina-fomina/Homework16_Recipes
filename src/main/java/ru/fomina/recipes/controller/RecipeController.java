package ru.fomina.recipes.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fomina.recipes.model.Ingredient;
import ru.fomina.recipes.model.Recipe;
import ru.fomina.recipes.service.RecipeService;

import java.util.List;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "CRUD-операции для работы с рецептами")
public class RecipeController {
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/add")
    @Operation(
            summary = "Добавление рецепта",
            description = "Метод добавления рецепта с указанием его названия, времени приготовления," +
                    "списка ингредиентов и шагов по приготовлению"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт добавлен в список",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка в параметрах запроса: не указано название рецепта",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        if (StringUtils.isBlank(recipe.getRecipeName())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(recipeService.addRecipe(recipe));
    }

    @GetMapping("/{recipeId}")
    @Operation(
            summary = "Получение рецепта из списка",
            description = "Метод получения данных о рецепте по его id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт получен из списка",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Рецепт в списке не найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    public ResponseEntity<Recipe> getRecipe(@PathVariable("recipeId") Integer recipeId) {
        Recipe recipe = recipeService.getRecipe(recipeId);
        if (ObjectUtils.isEmpty(recipe)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @GetMapping
    @Operation(
            summary = "Получение полного списка рецептов"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список рецептов получен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Список рецептов пуст",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        List<Recipe> allRecipes = recipeService.getAllRecipes();
        if (ObjectUtils.isEmpty(allRecipes)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allRecipes);
    }

    @PutMapping("/{recipeId}")
    @Operation(
            summary = "Редактирование данных о рецепте",
            description = "Метод редактирования данных о рецепте по его id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные о рецепте отредактированы",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "В списке нет рецепта с таким id",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    public ResponseEntity<Recipe> editRecipe(@PathVariable("recipeId") Integer recipeId, @RequestBody Recipe recipe) {
        Recipe newRecipe = recipeService.editRecipe(recipeId, recipe);
        if (ObjectUtils.isEmpty(newRecipe)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(newRecipe);
    }

    @DeleteMapping("/{recipeId}")
    @Operation(
            summary = "Удаление рецепта из списка",
            description = "Метод удаления рецепта по его id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные о рецепте удалены из списка",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "В списке нет рецепта с таким id",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    public ResponseEntity<String> deleteRecipe(@PathVariable("recipeId") Integer recipeId) {
        if (recipeService.deleteRecipe(recipeId)) {
            return ResponseEntity.ok("Рецепт номер " + recipeId + " удалён.");
        }
        return ResponseEntity.notFound().build();
    }
}
