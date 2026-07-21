package com.inforcol.seguros.service;

import com.inforcol.seguros.dto.BookResponseDto;
import com.inforcol.seguros.dto.CategoryDto;
import com.inforcol.seguros.model.Book;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class BookMapper {

    public BookResponseDto toDto(Book book) {
        if (book == null) return null;

        BookResponseDto dto = new BookResponseDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setIsbn(book.getIsbn());
        dto.setPrice(book.getPrice());

        // Aplanamos el autor para evitar recursión
        if (book.getAuthor() != null) {
            dto.setAuthorName(book.getAuthor().getName());
        }

        // Mapeamos las categorías asociadas
        if (book.getCategories() != null) {
            dto.setCategories(book.getCategories().stream()
                    .map(cat -> {
                        CategoryDto catDto = new CategoryDto();
                        catDto.setId(cat.getId());
                        catDto.setName(cat.getName());
                        return catDto;
                    })
                    .collect(Collectors.toList()));
        }
        return dto;
    }

}
