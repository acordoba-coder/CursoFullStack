package com.inforcol.seguros.service;

import com.inforcol.seguros.dto.CategoryRequestDto;
import com.inforcol.seguros.dto.CategoryResponseDto;
import com.inforcol.seguros.model.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {
    // Entidad -> DTO de Respuesta
    public CategoryResponseDto toDto(Category category) {
        if (category == null) return null;

        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(category.getId());
        dto.setName(category.getName());

        return dto;
    }

    // DTO de Petición -> Entidad
    public Category toEntity(CategoryRequestDto dto) {
        if (dto == null) return null;

        Category category = new Category();
        category.setName(dto.getName());

        return category;
    }
}
