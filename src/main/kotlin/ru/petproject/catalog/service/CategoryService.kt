package ru.petproject.catalog.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import ru.petproject.catalog.exception.ConstraintViolationException
import ru.petproject.catalog.repository.CategoryRepository
import ru.petproject.catalog.exception.NoDataFoundException
import ru.petproject.catalog.model.Category

@Service
@Transactional
class CategoryService(private val repository: CategoryRepository) {
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    fun getCategoryByName(name: String): Category {
        return repository.getByName(name)
            .orElseThrow { NoDataFoundException(name) }
    }

    fun addCategory(categoryName: String, parentCategoryName: String?): Category {
        val newCategory = Category(categoryName)

        if (!parentCategoryName.isNullOrBlank()) {
            val parentCategory = repository.getByName(parentCategoryName)
                .orElseThrow { NoDataFoundException(parentCategoryName) }
            newCategory.parent = parentCategory
        }
        return repository.save(newCategory)
    }

    fun move(categoryName: String, newCategoryName: String) {
        val category = repository.getByName(categoryName)
            .orElseThrow { NoDataFoundException(categoryName) }

        val oldCategory = category.parent
        val newCategory = repository.getByName(newCategoryName)
            .orElseThrow { NoDataFoundException(newCategoryName) }

        category.parent = newCategory
        oldCategory?.children?.remove(category)
        newCategory.children.add(category)
    }

    fun delete(categoryName: String) {
        val category = repository.getByName(categoryName)
            .orElseThrow { NoDataFoundException(categoryName) }

        if (!category.products.isNullOrEmpty() || !category.children.isNullOrEmpty()) {
            throw ConstraintViolationException(categoryName)
        }
        val parent = category.parent
        parent?.children?.remove(category)
        repository.delete(category)
    }
}
