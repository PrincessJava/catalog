package ru.petproject.catalog.to

import jakarta.validation.constraints.NotBlank
import java.io.Serializable

class ProductTO : Serializable {
    @field:NotBlank(message = "Name is mandatory")
    var name: String? = null

    @field:NotBlank(message = "Category name is mandatory")
    var category: String? = null
}
