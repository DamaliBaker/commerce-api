package io.github.damalibaker.commerceapi.catalog.category.mapper;

import io.github.damalibaker.commerceapi.catalog.category.dto.CategoryResponse;
import io.github.damalibaker.commerceapi.catalog.category.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryResponse toCategoryResponse(CategoryEntity categoryEntity) {
        return new CategoryResponse(
                categoryEntity.getId(),
                categoryEntity.getName()
        );
    }
}
