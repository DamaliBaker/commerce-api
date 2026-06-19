package io.github.damalibaker.commerceapi.cart.dto.response;

import io.github.damalibaker.commerceapi.catalog.product.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponse {
    private Long id;
    private Long productId;
    private String productName;
    private BigDecimal unitPrice;
    private Integer quantity;
    private BigDecimal lineTotal;
    private ProductStatus productStatus;
    private Integer availableStock;
    private boolean available;
}
