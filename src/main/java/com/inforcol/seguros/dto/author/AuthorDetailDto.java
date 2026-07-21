package com.inforcol.seguros.dto.author;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AuthorDetailDto {
    private Long id;
    private String biography;
    private LocalDate birthDate;
    private String nationality;
}
