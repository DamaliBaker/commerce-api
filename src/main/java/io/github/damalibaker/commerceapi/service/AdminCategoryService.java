package io.github.damalibaker.commerceapi.service;

import io.github.damalibaker.commerceapi.dto.request.CreateCategoryRequest;
import io.github.damalibaker.commerceapi.entity.CategoryEntity;

import java.util.List;

public interface AdminCategoryService {
    CategoryEntity createCategory(CreateCategoryRequest request);
    CategoryEntity updateCategory(Long id, CreateCategoryRequest request);
    void deleteCategoryById(Long id);
    List<CategoryEntity> getAllCategories();
    CategoryEntity getCategoryById(Long id);


}
