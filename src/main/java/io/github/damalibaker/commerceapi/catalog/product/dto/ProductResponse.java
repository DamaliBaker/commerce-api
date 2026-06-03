package io.github.damalibaker.commerceapi.catalog.product.dto;
import io.github.damalibaker.commerceapi.catalog.product.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private ProductStatus status;
    private Long categoryId;
    private String categoryName;
}
