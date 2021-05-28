package ru.petproject.catalog.to

import java.io.Serializable
import javax.validation.constraints.NotBlank

class ProductTO : Serializable {
    var name: @NotBlank(message = "Name is mandatory") String? = null
    var category: @NotBlank(message = "Category name is mandatory") String? = null
}
