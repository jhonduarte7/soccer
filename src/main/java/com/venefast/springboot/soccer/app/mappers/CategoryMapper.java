package com.venefast.springboot.soccer.app.mappers;

import com.venefast.springboot.soccer.app.models.dtos.CategoryDto;
import com.venefast.springboot.soccer.app.models.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDto toDto(Category entity) {
        if (entity == null) {
            return null;
        }
        return new CategoryDto(entity.getId(), entity.getName());
    }

    public Category toEntity(CategoryDto dto) {
        if (dto == null) {
            return null;
        }
        Category category = new Category();
        category.setId(dto.id());
        category.setName(dto.name());
        return category;
    }
}
