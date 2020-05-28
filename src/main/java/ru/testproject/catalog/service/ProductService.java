package ru.testproject.catalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Autowired
    public ProductService(ProductRepository repository, CategoryService categoryService) {
        this.repository = repository;
        this.categoryService = categoryService;
    }

    public List<Product> getByCategory(String categoryName) {
        Category category = categoryService.getCategoryByName(categoryName);
        NoDataFoundException.checkNotNull(category, categoryName);
        return category.getProducts();
    }

    public Product add(String productName, String categoryName) {
        Category category = categoryService.getCategoryByName(categoryName);
        NoDataFoundException.checkNotNull(category, categoryName);
        Product product = new Product(productName);
        product.setCategory(category);
        categoryService.addProduct(category, product);

        return repository.save(product);
    }

    public void move(String productName, String newCategoryName) {
        Category newCategory = categoryService.getCategoryByName(newCategoryName);
        Product product = repository.getByName(productName);
        Category oldCategory = categoryService.getCategoryByName(product.getCategory().getName());

        product.setCategory(newCategory);
        categoryService.addProduct(newCategory, product);
        categoryService.removeProduct(oldCategory, product);
    }

    public void delete(String productName) {
        Product product = repository.getByName(productName);
        product.getCategory().getProducts().remove(product);
        repository.delete(product);
    }
}
