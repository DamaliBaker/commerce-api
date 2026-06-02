package io.github.damalibaker.commerceapi.mapper;

import io.github.damalibaker.commerceapi.dto.response.CategoryResponse;
import io.github.damalibaker.commerceapi.entity.CategoryEntity;
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
