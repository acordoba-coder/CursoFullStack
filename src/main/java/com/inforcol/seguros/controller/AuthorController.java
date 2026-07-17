package com.inforcol.seguros.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inforcol.seguros.dto.AuthorDTO;
import com.inforcol.seguros.model.Author;
import com.inforcol.seguros.service.AuthorService;


@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
     public ResponseEntity<List<Author>> getAuhors(){
        return ResponseEntity.ok(this.authorService.listAuthors());
    }

    @PostMapping("/crear")
    public ResponseEntity<AuthorDTO> createAuthor(AuthorDTO authorDTO) {
        return ResponseEntity.ok(this.authorService.createAuthor(authorDTO));
    }

}
