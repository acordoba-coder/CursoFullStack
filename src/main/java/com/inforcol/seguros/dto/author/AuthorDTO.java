package com.inforcol.seguros.dto.author;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorDTO {

    private String nameAuthor;
    private String countryAuthor;

}
