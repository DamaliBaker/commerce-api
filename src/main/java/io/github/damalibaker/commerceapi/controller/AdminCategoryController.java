package io.github.damalibaker.commerceapi.controller;

import io.github.damalibaker.commerceapi.dto.request.CreateCategoryRequest;
import io.github.damalibaker.commerceapi.dto.response.CategoryResponse;
import io.github.damalibaker.commerceapi.entity.CategoryEntity;
import io.github.damalibaker.commerceapi.mapper.CategoryMapper;
import io.github.damalibaker.commerceapi.service.AdminCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin/categories")
public class AdminCategoryController {
    private final AdminCategoryService categoryService;
    private final CategoryMapper categoryMapper;


    public AdminCategoryController(AdminCategoryService categoryService,
                                   CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse createCategory(@RequestBody @Valid CreateCategoryRequest request) {
        return categoryMapper.toCategoryResponse(
                categoryService.createCategory(request)
        );
    }

    @PutMapping("/{id}")
    public CategoryResponse updateCategory(@RequestBody @Valid CreateCategoryRequest request,
                                           @PathVariable Long id) {
        return categoryMapper.toCategoryResponse(
                categoryService.updateCategory(id, request)
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }

    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        List<CategoryEntity> categories = categoryService.getAllCategories();
        List<CategoryResponse> response = new ArrayList<>();

        categories.forEach(categoryEntity -> response.add(categoryMapper.toCategoryResponse(categoryEntity)));

        return response;
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Long id) {
        return categoryMapper.toCategoryResponse(categoryService.getCategoryById(id));
    }
}
