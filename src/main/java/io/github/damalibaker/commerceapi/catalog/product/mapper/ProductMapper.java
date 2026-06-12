package io.github.damalibaker.commerceapi.catalog.product.mapper;

import io.github.damalibaker.commerceapi.catalog.product.dto.response.ProductResponse;
import io.github.damalibaker.commerceapi.catalog.product.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductResponse toProductResponse(ProductEntity product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getStatus(),
                product.getCategory().getId(),
                product.getCategory().getName()
        );
    }
}
