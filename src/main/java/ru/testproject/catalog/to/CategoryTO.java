package ru.testproject.catalog.to;

import javax.validation.constraints.NotBlank;

import java.io.Serializable;

public class CategoryTO implements Serializable {
    @NotBlank(message = "Category name is mandatory")
    public String name;
    @NotBlank(message = "New category name is mandatory")
    public String newCatName;
}
