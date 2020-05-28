package ru.testproject.catalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testproject.catalog.exception.NoDataFoundException;
import ru.testproject.catalog.model.Category;
import ru.testproject.catalog.model.Product;
import ru.testproject.catalog.repository.CategoryRepository;
import org.apache.commons.lang3.StringUtils;

@Service
@Transactional
public class CategoryService {
    private CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category getCategoryByName(String name) {
        Category category = repository.getByName(name);
        NoDataFoundException.checkNotNull(category, name);
        return repository.getByName(name);
    }

    public void addProduct(Category category, Product product) {
        category.getProducts().add(product);
        repository.saveAndFlush(category);
    }

    public void removeProduct(Category category, Product product) {
        category.getProducts().remove(product);
        repository.saveAndFlush(category);
    }

    public Category addCategory(String categoryName, String parentCategoryName) {
        Category newCategory = new Category(categoryName);

        if (StringUtils.isNotBlank(parentCategoryName)) {
            Category parentCategory = repository.getByName(categoryName);
            NoDataFoundException.checkNotNull(parentCategory, categoryName);
            newCategory.setParent(parentCategory);
        }
        return repository.save(newCategory);
    }

    public void move(String categoryName, String newCategoryName) {
        Category category = repository.getByName(categoryName);
        NoDataFoundException.checkNotNull(category, categoryName);
        Category oldCategory = category.getParent();
        Category newCategory = repository.getByName(newCategoryName);
        NoDataFoundException.checkNotNull(newCategory, newCategoryName);

        category.setParent(newCategory);
        if (oldCategory != null) {
            oldCategory.getChildren().remove(category);
            repository.save(oldCategory);
        }
        newCategory.getChildren().add(category);

        repository.save(newCategory);
        repository.save(category);
    }

    public void delete(String categoryName) {
        Category category = repository.getByName(categoryName);
        NoDataFoundException.checkNotNull(category, categoryName);

        Category parent = category.getParent();
        parent.getChildren().remove(category);

        repository.save(parent);
        repository.delete(category);
    }
}
