package ru.fomina.recipes.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.fomina.recipes.service.FilesService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServiceImpl implements FilesService {
    @Value("${path.to.ingredient.file}")
    private String ingredientFilePath;

    @Value("${name.of.ingredient.file}")
    private String ingredientFileName;

    @Value("${path.to.recipe.file}")
    private String recipeFilePath;

    @Value("${name.of.recipe.file}")
    private String recipeFileName;

    @Override
    public boolean saveIngredientToFile(String json) {
        try {
            cleanIngredientFile();
            Files.writeString(Path.of(ingredientFilePath, ingredientFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readIngredientFromFile() {
        try {
            return Files.readString(Path.of(ingredientFilePath, ingredientFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public File getIngredientFile() {
        return new File(ingredientFilePath + "/" + ingredientFileName);
    }

    @Override
    public boolean cleanIngredientFile() {
        Path path = Path.of(ingredientFilePath, ingredientFileName);
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveRecipeToFile(String json) {
        try {
            cleanRecipeFile();
            Files.writeString(Path.of(recipeFilePath, recipeFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readRecipeFromFile() {
        try {
            return Files.readString(Path.of(recipeFilePath, recipeFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public File getRecipeFile() {
        return new File(recipeFilePath + "/" + recipeFileName);
    }

    @Override
    public boolean cleanRecipeFile() {
        Path path = Path.of(recipeFilePath, recipeFileName);
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
