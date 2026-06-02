package io.github.damalibaker.commerceapi.service;


import io.github.damalibaker.commerceapi.dto.request.CreateCategoryRequest;
import io.github.damalibaker.commerceapi.entity.CategoryEntity;
import io.github.damalibaker.commerceapi.exception.CategoryAlreadyExistsException;
import io.github.damalibaker.commerceapi.exception.CategoryNotFoundException;
import io.github.damalibaker.commerceapi.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminCategoryServiceImpl implements AdminCategoryService {
    private final CategoryRepository categoryRepository;

    public AdminCategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryEntity createCategory(CreateCategoryRequest request) {
        String name = request.getName().trim();

        if (categoryRepository.existsByNameIgnoreCase(name)) {
            throw new CategoryAlreadyExistsException(name);
        }

        CategoryEntity category = CategoryEntity.builder()
                .name(name)
                .build();

        return categoryRepository.save(category);
    }

    @Override
    public CategoryEntity updateCategory(Long id, CreateCategoryRequest request) {
        CategoryEntity category = getCategoryById(id);
        String name = request.getName().trim();


        if (!category.getName().equalsIgnoreCase(name)
                && categoryRepository.existsByNameIgnoreCase(name)) {
            throw new CategoryAlreadyExistsException(name);
        }

        category.setName(request.getName());

        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        CategoryEntity category = getCategoryById(id);
        categoryRepository.delete(category);
    }

    @Override
    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryEntity getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }
}
