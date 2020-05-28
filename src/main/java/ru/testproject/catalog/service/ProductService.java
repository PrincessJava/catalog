package ru.testproject.catalog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.testproject.catalog.exception.NoDataFoundException;
import ru.testproject.catalog.model.Category;
import ru.testproject.catalog.model.Product;
import ru.testproject.catalog.repository.ProductRepository;

import java.util.List;

@Transactional
@Service
public class ProductService {
    private ProductRepository repository;
    private CategoryService categoryService;

    public ProductService(ProductRepository repository, CategoryService categoryService) {
        this.repository = repository;
        this.categoryService = categoryService;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Product> getByCategory(String categoryName) {
        Category category = categoryService.getCategoryByName(categoryName);
        return category.getProducts();
    }

    public Product add(String productName, String categoryName) {
        Category category = categoryService.getCategoryByName(categoryName);
        Product product = new Product(productName);
        product.setCategory(category);
        category.getProducts().add(product);

        return repository.save(product);
    }

    public void move(String productName, String newCategoryName) {
        Category newCategory = categoryService.getCategoryByName(newCategoryName);
        Product product = repository.getByName(productName)
                .orElseThrow(() -> new NoDataFoundException(productName));
        Category oldCategory = categoryService.getCategoryByName(product.getCategory().getName());

        product.setCategory(newCategory);
        newCategory.getProducts().add(product);
        oldCategory.getProducts().remove(product);
    }

    public void delete(String productName) {
        Product product = repository.getByName(productName)
                .orElseThrow(() -> new NoDataFoundException(productName));
        product.getCategory().getProducts().remove(product);
        repository.delete(product);
    }
}
