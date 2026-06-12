package io.github.damalibaker.commerceapi.cart.repository;

import io.github.damalibaker.commerceapi.cart.entity.CartEntity;
import io.github.damalibaker.commerceapi.cart.entity.CartItemEntity;
import io.github.damalibaker.commerceapi.catalog.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
    Optional<CartItemEntity> findByCartAndProduct(CartEntity cart, ProductEntity product);
    Optional<CartItemEntity> findByIdAndCart(Long id, CartEntity cart);
}
