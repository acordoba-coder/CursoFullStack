package com.inforcol.seguros.service;

import java.util.List;
import java.util.stream.Collectors;

import com.inforcol.seguros.dto.author.AuthorRequestDto;
import com.inforcol.seguros.dto.author.AuthorResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inforcol.seguros.model.Author;
import com.inforcol.seguros.repository.AuthorRepository;


@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    public AuthorService(AuthorRepository AuthorRepository) {
        this.authorRepository = AuthorRepository;
    }

    public AuthorResponseDto createAuthor(AuthorRequestDto dto) {
        Author savedAuthor = authorRepository.save(authorMapper.toEntity(dto));
        return authorMapper.toDto(savedAuthor);
    }

    public List<AuthorResponseDto> getAllAuthors() {

        return authorRepository.findAll()
                .stream()
                .map(authorMapper::toDto)
                .collect(Collectors.toList());
    }

}
