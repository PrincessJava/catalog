package ru.testproject.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.testproject.catalog.model.Category;
import ru.testproject.catalog.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getByName(String name);
}
