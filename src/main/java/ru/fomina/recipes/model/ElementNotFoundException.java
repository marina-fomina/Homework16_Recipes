package ru.fomina.recipes.model;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException() {
    }

    public ElementNotFoundException(String message) {
        super(message);
    }
}
