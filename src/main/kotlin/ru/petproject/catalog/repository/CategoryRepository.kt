package ru.petproject.catalog.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.petproject.catalog.model.Category

interface CategoryRepository : JpaRepository<Category, Long> {
    fun findByName(name: String): Category?
}
