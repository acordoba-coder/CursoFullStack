package com.inforcol.seguros.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.inforcol.seguros.dto.BookRequestDto;
import com.inforcol.seguros.dto.BookResponseDto;
import com.inforcol.seguros.dto.author.AuthorDetailDto;
import com.inforcol.seguros.dto.author.AuthorRequestDto;
import com.inforcol.seguros.dto.author.AuthorResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inforcol.seguros.model.Author;
import com.inforcol.seguros.model.Book;
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

    //UPDATE
    public AuthorResponseDto updateAuthor(Long id, AuthorRequestDto dto) {

    Author author = authorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Autor no encontrado con ID: " + id));

    author.setName(dto.getName());

    if (author.getAuthorDetail() != null) {
        author.getAuthorDetail().setBiography(dto.getAuthorDetail().getBiography());
        author.getAuthorDetail().setBirthDate(dto.getAuthorDetail().getBirthDate());
        author.getAuthorDetail().setNationality(dto.getAuthorDetail().getNationality());
    }

    Author updatedAuthor = authorRepository.save(author);

    return convertToDTO(updatedAuthor);
    }

    // READ (uno por id)
    public Optional<AuthorResponseDto> getBookById(Long id) {
        return authorRepository.findById(id)
               .map(this::convertToDTO);
    }

    // READ (por título)
    public List<AuthorResponseDto> getBookByName(String name) {
        return authorRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // DELETE
    public void deleteBook(Long id) {
        if (!authorMapper.existsById(id)) {
            throw new RuntimeException("No existe un author con el id: " + id);
        }

        authorMapper.deleteById(id);
    }





    private AuthorResponseDto convertToDTO(Author author) {

    AuthorResponseDto dto = new AuthorResponseDto();

    dto.setId(author.getId());
    dto.setName(author.getName());
    
    if (author.getAuthorDetail() != null) {

        AuthorDetailDto detailDto = new AuthorDetailDto();
        detailDto.setId(author.getAuthorDetail().getId());
        detailDto.setBiography(author.getAuthorDetail().getBiography());
        detailDto.setNationality(author.getAuthorDetail().getNationality());
        detailDto.setBirthDate(author.getAuthorDetail().getBirthDate());

        dto.setAuthorDetail(detailDto);
    }

    return dto;
    }

    

}
