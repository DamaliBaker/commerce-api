package io.github.damalibaker.commerceapi.catalog.category.service;

import io.github.damalibaker.commerceapi.catalog.category.entity.CategoryEntity;
import io.github.damalibaker.commerceapi.catalog.category.dto.CreateCategoryRequest;

import java.util.List;

public interface CategoryService {
    CategoryEntity createCategory(CreateCategoryRequest request);
    CategoryEntity updateCategory(Long id, CreateCategoryRequest request);
    void deleteCategoryById(Long id);
    List<CategoryEntity> getAllCategories();
    CategoryEntity getCategoryById(Long id);


}
