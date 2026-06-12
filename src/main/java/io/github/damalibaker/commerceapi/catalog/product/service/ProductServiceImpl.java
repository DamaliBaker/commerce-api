package io.github.damalibaker.commerceapi.catalog.product.service;

import io.github.damalibaker.commerceapi.catalog.category.service.CategoryService;
import io.github.damalibaker.commerceapi.catalog.category.entity.CategoryEntity;
import io.github.damalibaker.commerceapi.catalog.product.dto.request.CreateProductRequest;
import io.github.damalibaker.commerceapi.catalog.product.dto.request.UpdateProductStockRequest;
import io.github.damalibaker.commerceapi.catalog.product.entity.ProductEntity;
import io.github.damalibaker.commerceapi.catalog.product.repository.ProductRepository;
import io.github.damalibaker.commerceapi.catalog.product.dto.request.UpdateProductRequest;
import io.github.damalibaker.commerceapi.catalog.product.enums.ProductStatus;
import io.github.damalibaker.commerceapi.exception.product.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public ProductEntity createProduct(CreateProductRequest request) {
        CategoryEntity category = categoryService.getCategoryById(request.getCategoryId());

        ProductEntity product = ProductEntity.builder()
                .name(normalizeString(request.getName()))
                .category(category)
                .description(normalizeString(request.getDescription()))
                .price(request.getPrice())
                .status(ProductStatus.ACTIVE)
                .stockQuantity(request.getStockQuantity())
                .build();

        return productRepository.save(product);
    }

    @Override
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductEntity getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public ProductEntity updateProduct(Long id, UpdateProductRequest request) {
        ProductEntity product = getProductById(id);
        CategoryEntity category = categoryService.getCategoryById(request.getCategoryId());

        product.setName(normalizeString(request.getName()));
        product.setDescription(normalizeString(request.getDescription()));
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setCategory(category);

        return productRepository.save(product);
    }

    @Override
    public ProductEntity deactivateProduct(Long id) {
        ProductEntity product = getProductById(id);

        product.setStatus(ProductStatus.INACTIVE);

        return productRepository.save(product);
    }

    @Override
    public ProductEntity activateProduct(Long id) {
        ProductEntity product = getProductById(id);

        product.setStatus(ProductStatus.ACTIVE);

        return productRepository.save(product);
    }

    @Override
    public ProductEntity updateProductStock(Long id, UpdateProductStockRequest request) {
        ProductEntity product = getProductById(id);

        product.setStockQuantity(request.getStockQuantity());

        return productRepository.save(product);
    }

    private String normalizeString(String str) {
        if (str == null || str.isBlank()) {
            return null;
        }

        return str.trim();
    }
}
