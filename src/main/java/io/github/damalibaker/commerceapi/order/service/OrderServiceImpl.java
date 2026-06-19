package io.github.damalibaker.commerceapi.order.service;

import io.github.damalibaker.commerceapi.cart.entity.CartEntity;
import io.github.damalibaker.commerceapi.cart.entity.CartItemEntity;
import io.github.damalibaker.commerceapi.cart.service.CartService;
import io.github.damalibaker.commerceapi.catalog.product.entity.ProductEntity;
import io.github.damalibaker.commerceapi.catalog.product.enums.ProductStatus;
import io.github.damalibaker.commerceapi.catalog.product.repository.ProductRepository;
import io.github.damalibaker.commerceapi.exception.cart.InactiveProductException;
import io.github.damalibaker.commerceapi.exception.cart.InsufficientStockException;
import io.github.damalibaker.commerceapi.exception.order.EmptyCartException;
import io.github.damalibaker.commerceapi.exception.order.OrderNotFoundException;
import io.github.damalibaker.commerceapi.exception.product.ProductNotFoundException;
import io.github.damalibaker.commerceapi.order.entity.OrderEntity;
import io.github.damalibaker.commerceapi.order.entity.OrderItemEntity;
import io.github.damalibaker.commerceapi.order.enums.OrderStatus;
import io.github.damalibaker.commerceapi.order.repository.OrderRepository;
import io.github.damalibaker.commerceapi.user.entity.UserEntity;
import io.github.damalibaker.commerceapi.user.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CartService cartService;
    private final UserService userService;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            ProductRepository productRepository,
            CartService cartService,
            UserService userService
    ) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.cartService = cartService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public OrderEntity checkout() {
        UserEntity user = userService.getCurrentUser();
        CartEntity cart = cartService.getCurrentUserCart();

        if (cart.getItems().isEmpty()) {
            throw new EmptyCartException();
        }

        OrderEntity order = OrderEntity.builder()
                .user(user)
                .status(OrderStatus.CONFIRMED)
                .subtotal(BigDecimal.ZERO)
                .createdAt(LocalDateTime.now())
                .build();

        BigDecimal subtotal = BigDecimal.ZERO;

        for (CartItemEntity cartItem : cart.getItems()) {
            ProductEntity product = productRepository.findByIdForUpdate(cartItem.getProduct().getId())
                    .orElseThrow(() -> new ProductNotFoundException(cartItem.getProduct().getId()));

            validateProductForCheckout(product, cartItem.getQuantity());

            BigDecimal unitPrice = product.getPrice();
            BigDecimal lineTotal = unitPrice.multiply(BigDecimal.valueOf(cartItem.getQuantity()));

            OrderItemEntity orderItem = OrderItemEntity.builder()
                    .order(order)
                    .product(product)
                    .productName(product.getName())
                    .unitPrice(unitPrice)
                    .quantity(cartItem.getQuantity())
                    .lineTotal(lineTotal)
                    .build();

            order.getItems().add(orderItem);

            subtotal = subtotal.add(lineTotal);

            product.setStockQuantity(product.getStockQuantity() - cartItem.getQuantity());
        }

        order.setSubtotal(subtotal);

        OrderEntity savedOrder = orderRepository.save(order);

        cartService.clearCart();

        return savedOrder;
    }

    @Override
    public List<OrderEntity> getOrdersForCurrentUser() {
        UserEntity user = userService.getCurrentUser();

        return orderRepository.findByUserOrderByCreatedAtDesc(user);
    }

    @Override
    public OrderEntity getOrderForCurrentUser(Long orderId) {
        UserEntity user = userService.getCurrentUser();

        return orderRepository.findByIdAndUser(orderId, user)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    private void validateProductForCheckout(ProductEntity product, Integer quantity) {
       if (product.getStatus() != ProductStatus.ACTIVE) {
           throw new InactiveProductException(product.getName());
       }
       if (quantity > product.getStockQuantity()) {
           throw new InsufficientStockException(product.getName(), product.getStockQuantity());
       }
    }
}
