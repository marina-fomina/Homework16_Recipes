package ru.fomina.recipes.service;

import java.io.File;

public interface FilesService {
    boolean saveIngredientToFile(String json);

    String readIngredientFromFile();

    File getIngredientFile();

    boolean cleanIngredientFile();

    boolean saveRecipeToFile(String json);

    String readRecipeFromFile();

    File getRecipeFile();

    boolean cleanRecipeFile();
}
