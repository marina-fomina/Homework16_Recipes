package ru.fomina.recipes.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Objects;

public class Recipe {
    private final String recipeName;
    private int cookingTime;
    private ArrayList <Ingredient> ingredients = new ArrayList<>();
    private LinkedHashSet <String> steps = new LinkedHashSet<>();

    public Recipe(String recipeName, int cookingTime, ArrayList<Ingredient> ingredients, LinkedHashSet<String> steps) {
        if (recipeName == null || recipeName.isBlank()) {
            throw new NotEnoughData("Не указано название рецепта!");
        } else {
            this.recipeName = recipeName;
        }
        setCookingTime(cookingTime);
        if (ingredients == null || ingredients.size() == 0) {
            throw new NotEnoughData("Не заполнены ингредиенты!");
        } else {
            this.ingredients = ingredients;
        }
        if (steps == null || steps.size() == 0) {
            throw new NotEnoughData("Не указаны шаги приготовления!");
        } else {
            this.steps = steps;
        }
    }

    @Override
    public String toString() {
        return "Название рецепта - " + getRecipeName() + ". Время приготовления - " + getCookingTime() + " минут. \n " +
                "Список ингредиентов: \n" + getIngredients() + "\n Шаги приготовления: \n" + getSteps();
    }

    public String getRecipeName() {
        return recipeName;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        if (cookingTime > 0) {
            this.cookingTime = cookingTime;
        } else {
         throw new NotEnoughData("Неверно указано время приготовления блюда!");
        }
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public LinkedHashSet<String> getSteps() {
        return steps;
    }

    public void setSteps(LinkedHashSet<String> steps) {
        this.steps = steps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(recipeName, recipe.recipeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeName);
    }
}
