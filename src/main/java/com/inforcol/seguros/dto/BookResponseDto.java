package com.inforcol.seguros.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookResponseDto {
    private Long id;
    private String title;
    private String isbn;
    private Double price;
    private Long authorId;
    private String authorName; // Aplanamos la información del autor
    private List<CategoryDto> categories;
    
}
