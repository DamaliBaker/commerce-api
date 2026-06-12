package io.github.damalibaker.commerceapi.cart.repository;

import io.github.damalibaker.commerceapi.cart.entity.CartEntity;
import io.github.damalibaker.commerceapi.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
    Optional<CartEntity> findByUser(UserEntity user);
}
