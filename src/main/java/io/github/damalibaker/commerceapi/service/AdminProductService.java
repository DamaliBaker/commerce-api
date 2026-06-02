package io.github.damalibaker.commerceapi.service;

import io.github.damalibaker.commerceapi.dto.request.CreateProductRequest;
import io.github.damalibaker.commerceapi.dto.request.UpdateProductRequest;
import io.github.damalibaker.commerceapi.entity.ProductEntity;

import java.util.List;

public interface AdminProductService {
    ProductEntity createProduct(CreateProductRequest request);
    List<ProductEntity> getAllProducts();
    ProductEntity getProductById(Long id);
    ProductEntity updateProduct(Long id, UpdateProductRequest request);
    ProductEntity deactivateProduct(Long id);
    ProductEntity activateProduct(Long id);
}
