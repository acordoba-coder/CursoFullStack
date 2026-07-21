package com.inforcol.seguros.dto.author;

import lombok.Data;

@Data
public class AuthorRequestDto {
    private String name;
    private AuthorDetailDto authorDetail;
}
