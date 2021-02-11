package ru.testproject.catalog.to;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@ApiModel
public class ProductTO implements Serializable {
    @NotBlank(message = "Name is mandatory")
    public String name;
    @NotBlank(message = "Category name is mandatory")
    public String category;
}
