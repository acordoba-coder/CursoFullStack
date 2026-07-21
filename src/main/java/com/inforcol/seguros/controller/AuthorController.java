package com.inforcol.seguros.controller;

import com.inforcol.seguros.dto.BookResponseDto;
import com.inforcol.seguros.dto.author.AuthorRequestDto;
import com.inforcol.seguros.dto.author.AuthorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.inforcol.seguros.service.AuthorService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;


@RestController
@RequestMapping("/api/authors")
@Slf4j
public class AuthorController {

    private final AuthorService authorService;

    AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AuthorResponseDto> updateAuthor(
            @PathVariable Long id,
            @RequestBody AuthorRequestDto dto) {

        return ResponseEntity.ok(authorService.updateAuthor(id, dto));
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDto> createAuthor(@RequestBody AuthorRequestDto dto) {
        AuthorResponseDto response = authorService.createAuthor(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDto>> getAllAuthors() {
        List<AuthorResponseDto> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

     @GetMapping("filterid/{id}")
    public ResponseEntity<AuthorResponseDto> obtenerPorId(@RequestParam  Long id) {
        log.info("BookController -> ObtenerPorId {}", authorService.getBookById(id));
        return authorService.getBookById(id)
                .map(ResponseEntity::ok) 
                .orElse(ResponseEntity.notFound().build()); 
    } 

    @GetMapping("/filternm/{name}")
    public ResponseEntity<List<AuthorResponseDto>> obtenerPorTitulo(@RequestParam String name) {
        List<AuthorResponseDto> authors = authorService.getBookByName(name);
        log.info("BookController -> obtenerPorTitulo {}", authors);
        if (authors.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(authors);
    }



}
