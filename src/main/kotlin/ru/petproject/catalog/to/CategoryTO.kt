package ru.petproject.catalog.to

import java.io.Serializable
import javax.validation.constraints.NotBlank

class CategoryTO : Serializable {
    var name: @NotBlank(message = "Category name is mandatory") String? = null
    var newCatName: @NotBlank(message = "New category name is mandatory") String? = null
}
