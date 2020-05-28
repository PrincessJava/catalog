package ru.testproject.catalog.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.testproject.catalog.exception.NoDataFoundException;
import ru.testproject.catalog.model.Category;
import ru.testproject.catalog.repository.CategoryRepository;

@Service
@Transactional
public class CategoryService {
    private CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Category getCategoryByName(String name) {
        return repository.getByName(name)
                .orElseThrow(() -> new NoDataFoundException(name));
    }

    public Category addCategory(String categoryName, String parentCategoryName) {
        Category newCategory = new Category(categoryName);

        if (StringUtils.isNotBlank(parentCategoryName)) {
            Category parentCategory = repository.getByName(parentCategoryName)
                    .orElseThrow(() -> new NoDataFoundException(parentCategoryName));
            newCategory.setParent(parentCategory);
        }
        return repository.save(newCategory);
    }

    public void move(String categoryName, String newCategoryName) {
        Category category = repository.getByName(categoryName)
                .orElseThrow(() -> new NoDataFoundException(categoryName));
        Category oldCategory = category.getParent();
        Category newCategory = repository.getByName(newCategoryName)
                .orElseThrow(() -> new NoDataFoundException(newCategoryName));

        category.setParent(newCategory);
        if (oldCategory != null) {
            oldCategory.getChildren().remove(category);
        }
        newCategory.getChildren().add(category);
    }

    public void delete(String categoryName) {
        Category category = repository.getByName(categoryName)
                .orElseThrow(() -> new NoDataFoundException(categoryName));

        Category parent = category.getParent();
        parent.getChildren().remove(category);

        repository.delete(category);
    }
}
