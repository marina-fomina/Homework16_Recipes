package ru.fomina.recipes.service;

public interface FilesService {
    boolean saveIngredientToFile(String json);

    String readIngredientFromFile();

    boolean saveRecipeToFile(String json);

    String readRecipeFromFile();
}
