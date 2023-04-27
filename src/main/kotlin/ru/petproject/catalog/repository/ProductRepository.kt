package ru.petproject.catalog.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.petproject.catalog.model.Product
import java.util.*

interface ProductRepository : JpaRepository<Product, Long> {
    fun findByName(name: String): Product?
}
