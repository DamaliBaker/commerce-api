package io.github.damalibaker.commerceapi.catalog.product.controller;

import io.github.damalibaker.commerceapi.catalog.product.dto.request.CreateProductRequest;
import io.github.damalibaker.commerceapi.catalog.product.dto.response.ProductResponse;
import io.github.damalibaker.commerceapi.catalog.product.dto.request.UpdateProductRequest;
import io.github.damalibaker.commerceapi.catalog.product.dto.request.UpdateProductStockRequest;
import io.github.damalibaker.commerceapi.catalog.product.entity.ProductEntity;
import io.github.damalibaker.commerceapi.catalog.product.mapper.ProductMapper;
import io.github.damalibaker.commerceapi.catalog.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    public AdminProductController(ProductService productService,
                                  ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@Valid @RequestBody CreateProductRequest request) {
        return productMapper.toProductResponse(productService.createProduct(request));
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        List<ProductEntity> products = productService.getAllProducts();
        List<ProductResponse> response = new ArrayList<>();

        products.forEach(product -> response.add(productMapper.toProductResponse(product)));

        return response;
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable Long id,
                                         @Valid @RequestBody UpdateProductRequest request) {
        return productMapper.toProductResponse(productService.updateProduct(id, request));
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id) {
        return productMapper.toProductResponse(productService.getProductById(id));
    }

    @PatchMapping("/{id}/deactivate")
    public ProductResponse deactivateProduct(@PathVariable Long id) {
        return productMapper.toProductResponse(productService.deactivateProduct(id));
    }

    @PatchMapping("/{id}/activate")
    public ProductResponse activateProduct(@PathVariable Long id) {
        return productMapper.toProductResponse(productService.activateProduct(id));
    }

    @PatchMapping("/{id}/stock")
    public ProductResponse updateProductStock(@PathVariable Long id,
                                              @Valid @RequestBody UpdateProductStockRequest request) {
        return productMapper.toProductResponse(productService.updateProductStock(id, request));
    }
}



