package ru.testproject.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.testproject.catalog.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getByName(String name);
}
