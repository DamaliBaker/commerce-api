package io.github.damalibaker.commerceapi.cart.service;

import io.github.damalibaker.commerceapi.cart.dto.request.AddCartItemRequest;
import io.github.damalibaker.commerceapi.cart.dto.request.UpdateCartItemRequest;
import io.github.damalibaker.commerceapi.cart.entity.CartEntity;
import io.github.damalibaker.commerceapi.cart.entity.CartItemEntity;
import io.github.damalibaker.commerceapi.cart.repository.CartItemRepository;
import io.github.damalibaker.commerceapi.cart.repository.CartRepository;
import io.github.damalibaker.commerceapi.catalog.product.entity.ProductEntity;
import io.github.damalibaker.commerceapi.catalog.product.enums.ProductStatus;
import io.github.damalibaker.commerceapi.catalog.product.service.ProductService;
import io.github.damalibaker.commerceapi.exception.cart.CartItemNotFoundException;
import io.github.damalibaker.commerceapi.exception.cart.InactiveProductException;
import io.github.damalibaker.commerceapi.exception.cart.InsufficientStockException;
import io.github.damalibaker.commerceapi.exception.cart.InvalidCartQuantityException;
import io.github.damalibaker.commerceapi.user.entity.UserEntity;
import io.github.damalibaker.commerceapi.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserService userService;
    private final ProductService productService;
    private final CartItemRepository cartItemRepository;

    public CartServiceImpl (CartRepository cartRepository,
                            UserService userService,
                            ProductService productService,
                            CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.productService = productService;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public CartEntity getCurrentUserCart() {
        UserEntity user = userService.getCurrentUser();
        return cartRepository.findByUser(user)
                .orElseGet(() -> createCartForUser(user));
    }

    @Override
    public CartEntity addItemToCart(AddCartItemRequest request) {
        validateQuantity(request.getQuantity());

        CartEntity cart = getCurrentUserCart();
        ProductEntity product = productService.getProductById(request.getProductId());

        validateProductCanBeAdded(product, request.getQuantity());

        CartItemEntity item = cartItemRepository.findByCartAndProduct(cart, product).orElse(null);

        if (item == null) {
            item = CartItemEntity.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(request.getQuantity())
                    .build();

            cart.getItems().add(item);
        }
        else {
            int newQuantity = item.getQuantity() + request.getQuantity();

            if (newQuantity > product.getStockQuantity()) {
                throw new InsufficientStockException(product.getName(), product.getStockQuantity());
            }

            item.setQuantity(newQuantity);
        }

        return cartRepository.save(cart);
    }

    @Override
    public CartEntity updateCartItem(Long itemId, UpdateCartItemRequest request) {
        validateQuantity(request.getQuantity());

        CartEntity cart = getCurrentUserCart();

        CartItemEntity item = cartItemRepository.findByIdAndCart(itemId, cart)
                .orElseThrow(() -> new CartItemNotFoundException(itemId));

        ProductEntity product = item.getProduct();

        validateProductCanBeAdded(product, request.getQuantity());

        item.setQuantity(request.getQuantity());

        cartItemRepository.save(item);

        return cart;
    }

    @Override
    public CartEntity removeCartItem(Long itemId) {
        CartEntity cart = getCurrentUserCart();

        CartItemEntity item = cartItemRepository.findByIdAndCart(itemId, cart)
                .orElseThrow(() -> new CartItemNotFoundException(itemId));

        cart.getItems().remove(item);

        return cartRepository.save(cart);
    }

    @Override
    public CartEntity clearCart() {
        CartEntity cart = getCurrentUserCart();

        cart.getItems().clear();

        return cartRepository.save(cart);
    }

    private CartEntity createCartForUser(UserEntity user) {
        CartEntity cart = CartEntity.builder()
                .user(user)
                .build();

        return cartRepository.save(cart);
    }

    private void validateProductCanBeAdded(ProductEntity product, Integer quantity) {
        if (product.getStatus() != ProductStatus.ACTIVE) {
            throw new InactiveProductException(product.getName());
        }

        if (quantity > product.getStockQuantity()) {
            throw new InsufficientStockException(product.getName(), product.getStockQuantity());
        }
    }

    private void validateQuantity(Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new InvalidCartQuantityException();
        }
    }
}
