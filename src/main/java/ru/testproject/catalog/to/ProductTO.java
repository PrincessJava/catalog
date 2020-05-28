package ru.testproject.catalog.to;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class ProductTO implements Serializable {
    @NotBlank(message = "Name is mandatory")
    public String name;
    @NotBlank(message = "Category name is mandatory")
    public String category;
}
