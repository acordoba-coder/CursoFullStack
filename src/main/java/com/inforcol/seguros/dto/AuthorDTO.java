package com.inforcol.seguros.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorDTO {

    private String nameAuthor;
    private String countryAuthor;

}
