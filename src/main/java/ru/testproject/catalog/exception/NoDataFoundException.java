package ru.testproject.catalog.exception;

import com.sun.istack.Nullable;

public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException(String name) {
        super("No data found: " + name);
    }

    public static <T> T checkNotNull(@Nullable T value, String name) throws NoDataFoundException {
        if (value == null) {
            throw new NoDataFoundException(name);
        } else {
            return value;
        }
    }
}
