package io.github.damalibaker.commerceapi.catalog.product.repository;

import io.github.damalibaker.commerceapi.catalog.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
