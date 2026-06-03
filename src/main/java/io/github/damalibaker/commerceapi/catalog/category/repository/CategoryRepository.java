package io.github.damalibaker.commerceapi.catalog.category.repository;

import io.github.damalibaker.commerceapi.catalog.category.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    boolean existsByNameIgnoreCase(String name);
}
