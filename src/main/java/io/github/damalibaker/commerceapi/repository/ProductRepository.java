package io.github.damalibaker.commerceapi.repository;

import io.github.damalibaker.commerceapi.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
