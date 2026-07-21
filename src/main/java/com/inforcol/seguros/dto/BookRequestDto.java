package com.inforcol.seguros.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookRequestDto {
    private String title;
    private String isbn;
    private Double price;
    private Long authorId;          // ID del autor al que pertenece
    private List<Long> categoryIds;
}
