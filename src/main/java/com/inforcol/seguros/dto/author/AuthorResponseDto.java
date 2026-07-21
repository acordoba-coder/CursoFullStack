package com.inforcol.seguros.dto.author;

import com.inforcol.seguros.dto.BookSimpleDto;
import com.inforcol.seguros.model.AuthorDetail;

import lombok.Data;

import java.util.List;

@Data
public class AuthorResponseDto {
    private Long id;
    private String name;
    private AuthorDetailDto authorDetail;
    private List<BookSimpleDto> books; // DTO que NO incluye nuevamente al autor
}

