package ru.petproject.catalog.controller

import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import ru.petproject.catalog.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.http.ResponseEntity
import ru.petproject.catalog.model.Product
import org.springframework.web.bind.annotation.PostMapping
import ru.petproject.catalog.to.ProductTO
import org.springframework.web.bind.annotation.PutMapping
import java.lang.Void
import org.springframework.web.bind.annotation.DeleteMapping

@RestController
@RequestMapping("/products")
class ProductController(private val productService: ProductService) {
    @GetMapping("/{categoryName}")
    fun getByCategory(@PathVariable @NotNull categoryName: String): ResponseEntity<List<Product>> {
        return ResponseEntity.ok(productService.getByCategory(categoryName))
    }

    @PostMapping("/add")
    fun add(productTO: @Valid ProductTO): ResponseEntity<Product> {
        return ResponseEntity.ok(productService.add(productTO.name!!, productTO.category!!))
    }

    @PutMapping("/move")
    fun move(productTO: @Valid ProductTO): ResponseEntity<Void> {
        productService.move(productTO.name!!, productTO.category!!)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{name}")
    fun remove(@PathVariable name: String): ResponseEntity<Void> {
        productService.delete(name)
        return ResponseEntity.ok().build()
    }
}
