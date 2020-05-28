package ru.testproject.catalog.exception;

public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException(String name) {
        super("No data found: " + name);
    }
}
