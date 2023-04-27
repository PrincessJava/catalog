package ru.petproject.catalog.to

import jakarta.validation.constraints.NotBlank
import java.io.Serializable

class CategoryTO : Serializable {
    var name: @NotBlank(message = "Category name is mandatory") String? = null
    var newCatName: @NotBlank(message = "New category name is mandatory") String? = null
}
