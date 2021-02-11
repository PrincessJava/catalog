package ru.testproject.catalog.controller;

import com.sun.istack.Nullable;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.testproject.catalog.model.Category;
import ru.testproject.catalog.service.CategoryService;
import ru.testproject.catalog.to.CategoryTO;

import javax.validation.Valid;

@RestController
@Api(tags = "Категории")
@RequestMapping(path = "/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<Category> get(@PathVariable String name) {
        return ResponseEntity.ok(categoryService.getCategoryByName(name));
    }

    @PostMapping("/add/{name}")
    public ResponseEntity<Category> add(@PathVariable String name, @RequestParam @Nullable String parentName) {
        return ResponseEntity.ok(categoryService.addCategory(name, parentName));
    }

    @PutMapping("/move")
    public ResponseEntity<Void> move(@Valid CategoryTO categoryTO) {
        categoryService.move(categoryTO.name, categoryTO.newCatName);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/remove/{name}")
    public ResponseEntity<Void> remove(@PathVariable String name) {
        categoryService.delete(name);
        return ResponseEntity.ok().build();
    }
}
