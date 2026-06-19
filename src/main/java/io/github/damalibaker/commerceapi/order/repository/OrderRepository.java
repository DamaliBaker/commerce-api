package io.github.damalibaker.commerceapi.order.repository;

import io.github.damalibaker.commerceapi.order.entity.OrderEntity;
import io.github.damalibaker.commerceapi.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByUserOrderByCreatedAtDesc(UserEntity user);
    Optional<OrderEntity> findByIdAndUser(Long id, UserEntity user);
}
