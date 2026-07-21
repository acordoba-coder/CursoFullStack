package com.inforcol.seguros.service;

import com.inforcol.seguros.dto.CategoryRequestDto;
import com.inforcol.seguros.dto.CategoryResponseDto;
import com.inforcol.seguros.model.Category;
import com.inforcol.seguros.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    public CategoryResponseDto createCategory(CategoryRequestDto dto) {
        // Validar que no exista otra categoría con el mismo nombre
        if (categoryRepository.existsByName(dto.getName())) {
            throw new RuntimeException("Ya existe una categoría con el nombre: " + dto.getName());
        }

        Category category = categoryMapper.toEntity(dto);
        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.toDto(savedCategory);
    }

    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }
}
