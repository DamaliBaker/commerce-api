package io.github.damalibaker.commerceapi.catalog.product.repository;

import io.github.damalibaker.commerceapi.catalog.category.entity.CategoryEntity;
import io.github.damalibaker.commerceapi.catalog.product.entity.ProductEntity;
import io.github.damalibaker.commerceapi.catalog.product.enums.ProductStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from ProductEntity p where p.id = :id")
    Optional<ProductEntity> findByIdForUpdate(@Param("id") Long id);
    List<ProductEntity> findByStatus(ProductStatus status);
    Optional<ProductEntity> findByIdAndStatus(Long id, ProductStatus status);
    List<ProductEntity> findByCategoryAndStatus(CategoryEntity category, ProductStatus status);
}
