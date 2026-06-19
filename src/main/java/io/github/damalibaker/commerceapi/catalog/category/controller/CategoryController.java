package io.github.damalibaker.commerceapi.catalog.category.controller;

import io.github.damalibaker.commerceapi.catalog.category.dto.CategoryResponse;
import io.github.damalibaker.commerceapi.catalog.category.mapper.CategoryMapper;
import io.github.damalibaker.commerceapi.catalog.category.service.CategoryService;
import io.github.damalibaker.commerceapi.catalog.product.dto.response.ProductResponse;
import io.github.damalibaker.commerceapi.catalog.product.mapper.ProductMapper;
import io.github.damalibaker.commerceapi.catalog.product.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final ProductService productService;
    private final ProductMapper productMapper;

    public CategoryController(
            CategoryService categoryService,
            CategoryMapper categoryMapper,
            ProductService productService,
            ProductMapper productMapper
    ) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories()
                .stream()
                .map(categoryMapper::toCategoryResponse)
                .toList();
    }

    @GetMapping("/{categoryId}/products")
    public List<ProductResponse> getActiveProductsByCategory(@PathVariable Long categoryId) {
        return productService.getActiveProductsByCategory(categoryId)
                .stream()
                .map(productMapper::toProductResponse)
                .toList();
    }
}
