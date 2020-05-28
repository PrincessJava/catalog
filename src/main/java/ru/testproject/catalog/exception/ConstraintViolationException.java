package ru.testproject.catalog.exception;

public class ConstraintViolationException extends RuntimeException {
    public ConstraintViolationException(String name) {
        super("Category " + name + " has subcategories or contains products");
    }
}
