package com.inforcol.seguros.controller;

import java.util.List;

import com.inforcol.seguros.dto.BookRequestDto;
import com.inforcol.seguros.dto.BookResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inforcol.seguros.model.Book;
import com.inforcol.seguros.service.BookService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    // CREATE: POST /api/books
    @PostMapping("/create")
    public ResponseEntity<BookResponseDto> createBook(@RequestBody BookRequestDto dto) {
        BookResponseDto response = bookService.createBook(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BookResponseDto> updateBook(
            @PathVariable Long id,
            @RequestBody BookRequestDto dto) {

        BookResponseDto response = bookService.updateBook(id, dto);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getBook(){
        log.info("BookController -> getBook");
        return ResponseEntity.ok(this.bookService.listBooks());
    }

     @GetMapping("filterid/{id}")
    public ResponseEntity<BookResponseDto> obtenerPorId(@RequestParam  Long id) {
        log.info("BookController -> ObtenerPorId {}", bookService.getBookById(id));
        return bookService.getBookById(id)
                .map(ResponseEntity::ok) 
                .orElse(ResponseEntity.notFound().build()); 
    } 

    @GetMapping("/filtertt/{titulo}")
    public ResponseEntity<List<BookResponseDto>> obtenerPorTitulo(@RequestParam String title) {
        List<BookResponseDto> books = bookService.getBookByTitulo(title);
        log.info("BookController -> obtenerPorTitulo {}", books);
        if (books.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(books);
    } 

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        bookService.deleteBook(id);
        log.info("BookController -> eliminar");
        return ResponseEntity.noContent().build(); 
    }





}
