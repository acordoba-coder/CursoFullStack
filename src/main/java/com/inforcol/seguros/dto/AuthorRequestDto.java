package com.inforcol.seguros.dto;

import lombok.Data;

@Data
public class AuthorRequestDto {
    private String name;
    private AuthorDetailDto authorDetail;
}
