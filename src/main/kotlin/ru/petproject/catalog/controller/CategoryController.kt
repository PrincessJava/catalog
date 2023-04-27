package ru.petproject.catalog.controller

import jakarta.annotation.Nullable
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import ru.petproject.catalog.service.CategoryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.PutMapping
import ru.petproject.catalog.to.CategoryTO
import java.lang.Void
import org.springframework.web.bind.annotation.DeleteMapping
import ru.petproject.catalog.model.Category

@RestController
@RequestMapping("/categories")
class CategoryController(private val categoryService: CategoryService) {

    @GetMapping("/{name}")
    fun get(@PathVariable name: String): ResponseEntity<Category> {
        return ResponseEntity.ok(categoryService.getCategoryByName(name))
    }

    @PostMapping("/{name}")
    fun add(@PathVariable name: String, @RequestParam @Nullable parentName: String?): ResponseEntity<Category> {
        return ResponseEntity.ok(categoryService.addCategory(name, parentName))
    }

    @PutMapping("/move")
    fun move(categoryTO: @Valid CategoryTO): ResponseEntity<Void> {
        categoryService.move(categoryTO.name!!, categoryTO.newCatName!!)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{name}")
    fun remove(@PathVariable name: String): ResponseEntity<Void> {
        categoryService.delete(name)
        return ResponseEntity.ok().build()
    }
}
