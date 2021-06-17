package ru.petproject.catalog.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import ru.petproject.catalog.exception.ConstraintViolationException
import ru.petproject.catalog.repository.CategoryRepository
import ru.petproject.catalog.exception.NoDataFoundException
import ru.petproject.catalog.model.Category
import ru.petproject.catalog.model.enum.Message

@Service
@Transactional
class CategoryService(private val repository: CategoryRepository) {

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    fun getCategoryByName(name: String): Category {
        return repository.findByName(name) ?: throw NoDataFoundException(name)
    }

    fun addCategory(categoryName: String, parentCategoryName: String?): Category {
        if (repository.findByName(categoryName) != null) {
            throw ConstraintViolationException(
                Message.getFormattedMessage(
                    Message.CATEGORY_ALREADY_EXISTS,
                    categoryName
                )
            )
        }

        val newCategory = Category(categoryName)

        if (!parentCategoryName.isNullOrBlank()) {
            val parentCategory = repository.findByName(categoryName) ?: throw NoDataFoundException(categoryName)
            newCategory.parent = parentCategory
        }
        return repository.save(newCategory)
    }

    fun move(categoryName: String, newCategoryName: String) {
        val category = repository.findByName(categoryName) ?: throw NoDataFoundException(categoryName)

        val oldCategory = category.parent
        val newCategory = repository.findByName(categoryName) ?: throw NoDataFoundException(categoryName)

        category.parent = newCategory
        oldCategory?.children?.remove(category)
        newCategory.children.add(category)
    }

    fun delete(categoryName: String) {
        val category = repository.findByName(categoryName) ?: throw NoDataFoundException(categoryName)

        if (!category.products.isNullOrEmpty() || !category.children.isNullOrEmpty()) {
            throw ConstraintViolationException(
                Message.getFormattedMessage(Message.CATEGORY_HAS_CHILDREN, categoryName)
            )
        }
        val parent = category.parent
        parent?.children?.remove(category)
        repository.delete(category)
    }
}
