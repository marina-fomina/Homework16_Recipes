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
import ru.fomina.recipes.service.IngredientService;

import java.util.List;



@RestController
@RequestMapping("/ingredient")
@Tag(name = "Ингредиенты", description = "CRUD-операции для работы с ингредиентами")
public class IngredientController {

    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping("/add")
    @Operation(
            summary = "Добавление ингредиента",
            description = "Метод добавления ингредиента с указанием его названия, количества и единицы измерения"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент добавлен в список",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка в параметрах запроса: не указано название ингредиента",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                            )
                    }
            )
    })
    public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient) {
        if (StringUtils.isBlank(ingredient.getIngredientName())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(ingredientService.addIngredient(ingredient));
    }

    @GetMapping("/{ingredientId}")
    @Operation(
            summary = "Получение ингредиента из списка",
            description = "Метод получения данных об ингредиенте по его id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент получен из списка",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ингредиент в списке не найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                            )
                    }
            )
    })
    public ResponseEntity<Ingredient> getIngredient(@PathVariable("ingredientId") Integer ingredientId) {
        Ingredient ingredient = ingredientService.getIngredient(ingredientId);
        if (ObjectUtils.isEmpty(ingredient)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @GetMapping
    @Operation(
            summary = "Получение полного списка ингредиентов"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список ингредиентов получен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Список ингредиентов пуст",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                            )
                    }
            )
    })
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        List<Ingredient> allIngredients = ingredientService.getAllIngredients();
        if (ObjectUtils.isEmpty(allIngredients)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allIngredients);
    }

    @PutMapping("/{ingredientId}")
    @Operation(
            summary = "Редактирование данных об ингредиенте",
            description = "Метод редактирования данных об ингредиенте по его id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные об ингредиенте отредактированы",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "В списке нет ингредиента с таким id",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                            )
                    }
            )
    })
    public ResponseEntity<Ingredient> editIngredient(@PathVariable("ingredientId") Integer ingredientId, @RequestBody Ingredient ingredient) {
        Ingredient newIngredient = ingredientService.editIngredient(ingredientId, ingredient);
        if (ObjectUtils.isEmpty(newIngredient)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(newIngredient);
    }

    @DeleteMapping("/{ingredientId}")
    @Operation(
            summary = "Удаление ингредиента из списка",
            description = "Метод удаления ингредиента по его id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные об ингредиенте удалены из списка",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "В списке нет ингредиента с таким id",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                            )
                    }
            )
    })
    public ResponseEntity<String > deleteIngredient(@PathVariable("ingredientId") Integer ingredientId) {
        if (ingredientService.deleteIngredient(ingredientId)) {
            return ResponseEntity.ok("Ингредиент номер " + ingredientId + " удалён.");
        }
        return ResponseEntity.notFound().build();
    }
}
