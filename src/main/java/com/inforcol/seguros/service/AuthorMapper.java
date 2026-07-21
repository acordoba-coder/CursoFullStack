package com.inforcol.seguros.service;

import com.inforcol.seguros.dto.author.AuthorDetailDto;
import com.inforcol.seguros.dto.author.AuthorRequestDto;
import com.inforcol.seguros.dto.author.AuthorResponseDto;
import com.inforcol.seguros.dto.BookSimpleDto;
import com.inforcol.seguros.model.Author;
import com.inforcol.seguros.model.AuthorDetail;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class AuthorMapper {
    // Entidad -> DTO de Respuesta
    public AuthorResponseDto toDto(Author author) {
        if (author == null) return null;

        AuthorResponseDto dto = new AuthorResponseDto();
        dto.setId(author.getId());
        dto.setName(author.getName());

        // Mapeo de AuthorDetail
        if (author.getAuthorDetail() != null) {
            AuthorDetailDto detailDto = new AuthorDetailDto();
            detailDto.setId(author.getAuthorDetail().getId());
            detailDto.setBiography(author.getAuthorDetail().getBiography());
            detailDto.setBirthDate(author.getAuthorDetail().getBirthDate());
            detailDto.setNationality(author.getAuthorDetail().getNationality());
            dto.setAuthorDetail(detailDto);
        }

        // Mapeo de Lista de Libros
        if (author.getBooks() != null) {
            dto.setBooks(author.getBooks().stream().map(book -> {
                BookSimpleDto bookDto = new BookSimpleDto();
                bookDto.setId(book.getId());
                bookDto.setTitle(book.getTitle());
                bookDto.setIsbn(book.getIsbn());
                bookDto.setPrice(book.getPrice());
                return bookDto;
            }).collect(Collectors.toList()));
        } else {
            dto.setBooks(new ArrayList<>());
        }

        return dto;
    }

    // DTO de Petición -> Entidad
    public Author toEntity(AuthorRequestDto dto) {
        if (dto == null) return null;

        Author author = new Author();
        author.setName(dto.getName());

        if (dto.getAuthorDetail() != null) {
            AuthorDetail detail = new AuthorDetail();
            detail.setBiography(dto.getAuthorDetail().getBiography());
            detail.setBirthDate(dto.getAuthorDetail().getBirthDate());
            detail.setNationality(dto.getAuthorDetail().getNationality());

            // Vincular bidireccionalmente
            author.setAuthorDetail(detail);
            detail.setAuthor(author);
        }

        return author;
    }
}
