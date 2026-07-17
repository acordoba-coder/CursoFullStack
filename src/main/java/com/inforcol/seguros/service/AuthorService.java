package com.inforcol.seguros.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.inforcol.seguros.dto.AuthorDTO;
import com.inforcol.seguros.model.Author;
import com.inforcol.seguros.model.Book;
import com.inforcol.seguros.repository.AuthorRepository;
import com.inforcol.seguros.repository.BookRepository;


@Service
public class AuthorService {

    private final AuthorRepository AuthorRepository;

    public AuthorService(AuthorRepository AuthorRepository) {
        this.AuthorRepository = AuthorRepository;
    }

    public List<Author> listAuthors() {
        return AuthorRepository.findAll();
    }


    public AuthorDTO createAuthor(AuthorDTO authorDTO) {

        Author author = new Author();
        author.setName(authorDTO.getNameAuthor());
        author.setCountry(authorDTO.getCountryAuthor());

        AuthorRepository.save(author);

        return AuthorDTO.builder()
                .nameAuthor(author.getName())
                .countryAuthor(author.getCountry())
                .build();
    }

}
