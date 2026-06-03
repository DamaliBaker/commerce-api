package io.github.damalibaker.commerceapi.catalog.product.service;

import io.github.damalibaker.commerceapi.catalog.product.dto.CreateProductRequest;
import io.github.damalibaker.commerceapi.catalog.product.entity.ProductEntity;
import io.github.damalibaker.commerceapi.catalog.product.dto.UpdateProductRequest;

import java.util.List;

public interface AdminProductService {
    ProductEntity createProduct(CreateProductRequest request);
    List<ProductEntity> getAllProducts();
    ProductEntity getProductById(Long id);
    ProductEntity updateProduct(Long id, UpdateProductRequest request);
    ProductEntity deactivateProduct(Long id);
    ProductEntity activateProduct(Long id);
}
