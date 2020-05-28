package ru.testproject.catalog.controller;

import com.sun.istack.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.testproject.catalog.model.Product;
import ru.testproject.catalog.service.ProductService;
import ru.testproject.catalog.to.ProductTO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get-by-category/{categoryName}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable @NotNull String categoryName) {
        return ResponseEntity.ok(productService.getByCategory(categoryName));
    }

    @PostMapping("/add")
    public ResponseEntity<Product> add(@Valid ProductTO productTO) {
        return ResponseEntity.ok(productService.add(productTO.name, productTO.category));
    }

    @PutMapping("/move")
    public ResponseEntity<Void> move(@Valid ProductTO productTO) {
        productService.move(productTO.name, productTO.category);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/remove/{name}")
    public ResponseEntity<Void> remove(@PathVariable String name) {
        productService.delete(name);
        return ResponseEntity.ok().build();
    }
}
