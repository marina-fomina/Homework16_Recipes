package ru.fomina.recipes.model;

import java.util.Objects;

public class Ingredient {
    private final String ingredientName;
    private int quantity;
    private String measureUnit;

    public Ingredient(String ingredientName, int quantity, String measureUnit) {
        if (ingredientName == null || ingredientName.isBlank()) {
            throw new NotEnoughData("Не указано название ингредиента!");
        } else {
            this.ingredientName = ingredientName;
        }
        setQuantity(quantity);
        setMeasureUnit(measureUnit);
    }

    @Override
    public String toString() {
        return "Ингредиент - " + getIngredientName() + ". Количество - " + getQuantity() + " " + getMeasureUnit();
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            throw new NotEnoughData("Неверно указано количество ингредиента!");
        }

    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        if (measureUnit == null || measureUnit.isBlank()) {
            throw new NotEnoughData("Не указана единициа измерения ингредиента!");
        } else {
            this.measureUnit = measureUnit;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient ingredient = (Ingredient) o;
        return Objects.equals(ingredientName, ingredient.ingredientName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientName);
    }
}
