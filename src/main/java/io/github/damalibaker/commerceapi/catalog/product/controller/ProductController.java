package io.github.damalibaker.commerceapi.catalog.product.controller;

import io.github.damalibaker.commerceapi.catalog.product.dto.response.ProductResponse;
import io.github.damalibaker.commerceapi.catalog.product.mapper.ProductMapper;
import io.github.damalibaker.commerceapi.catalog.product.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService,
                             ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping
    public List<ProductResponse> getActiveProducts() {
        return productService.getActiveProducts()
                .stream()
                .map(productMapper::toProductResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ProductResponse getActiveProductById(@PathVariable Long id) {
        return productMapper.toProductResponse(
                productService.getActiveProductById(id)
        );
    }
}
