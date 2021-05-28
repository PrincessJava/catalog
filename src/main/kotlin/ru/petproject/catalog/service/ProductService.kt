package ru.petproject.catalog.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import ru.petproject.catalog.repository.ProductRepository
import ru.petproject.catalog.model.Product
import ru.petproject.catalog.exception.NoDataFoundException

@Transactional
@Service
class ProductService(
    private val repository: ProductRepository,
    private val categoryService: CategoryService
) {

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    fun getByCategory(categoryName: String): List<Product> {
        val category = categoryService.getCategoryByName(categoryName)
        return category.products
    }

    fun add(productName: String, categoryName: String): Product {
        val category = categoryService.getCategoryByName(categoryName)
        val product = Product(productName).apply { this.category = category }
        category.products.add(product)
        return repository.save(product)
    }

    fun move(productName: String, newCategoryName: String) {
        val newCategory = categoryService.getCategoryByName(newCategoryName)
        val product = repository.getByName(productName)
            .orElseThrow { NoDataFoundException(productName) }

        val oldCategory = categoryService.getCategoryByName(product.category!!.name)
        product.category = newCategory
        newCategory.products.add(product)
        oldCategory.products.remove(product)
    }

    fun delete(productName: String) {
        val product = repository.getByName(productName)
            .orElseThrow { NoDataFoundException(productName) }

        product.category!!.products.remove(product)
        repository.delete(product)
    }
}
