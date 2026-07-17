package com.inforcol.seguros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping
    public ResponseEntity<List<Book>> getBook(){
        log.info("BookController -> getBook");
        return ResponseEntity.ok(this.bookService.listBooks());
    }

    @PostMapping("/create")
    public ResponseEntity<Book> create(@RequestBody Book book) {
        // Logic to save the product to the database
        Book savedProduct = this.bookService.createbook(book);
        log.info("BookController -> Create {}", book);
        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping("filterid/{id}")
    public ResponseEntity<Book> obtenerPorId(@RequestParam  Long id) {
        log.info("BookController -> ObtenerPorId {}", bookService.getBookById(id));
        return bookService.getBookById(id)
                .map(ResponseEntity::ok) 
                .orElse(ResponseEntity.notFound().build()); 
    } 

    @GetMapping("/filtertt/{titulo}")
    public ResponseEntity<List<Book>> obtenerPorTitulo(@RequestParam String titulo) {
        List<Book> books = bookService.getBookByTitulo(titulo);

        log.info("BookController -> obtenerPorTitulo {}", books);

        if (books.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(books);
    } 

     
    @PutMapping("edit/{id}")
    public ResponseEntity<Book> actualizar(@PathVariable Long id, @RequestBody Book book) {
        Book updatedProduct = bookService.updateBook(id, book);
        log.info("BookController -> actualizar {}", bookService.updateBook(id, book));
        return ResponseEntity.ok(updatedProduct); 
    }

    
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        bookService.deleteBook(id);
        log.info("BookController -> eliminar");
        return ResponseEntity.noContent().build(); 
    }

}
