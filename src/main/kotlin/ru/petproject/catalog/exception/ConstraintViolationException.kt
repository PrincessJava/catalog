package ru.petproject.catalog.exception

import java.lang.RuntimeException

class ConstraintViolationException(name: String) :
    RuntimeException("Category $name has subcategories or contains products")
