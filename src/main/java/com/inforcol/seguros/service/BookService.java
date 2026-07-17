package com.inforcol.seguros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inforcol.seguros.model.Book;
import com.inforcol.seguros.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository BookRepository;

    public BookService(BookRepository BookRepository) {
        this.BookRepository = BookRepository;
    }

    
    public Book createbook( Book book){
        return this.BookRepository.save(book);
    }

    // READ (todos)
    public List<Book> listBooks() {
        return BookRepository.findAll();
    }

    // READ (uno por id)
    public Optional<Book> getBookById(Long id) {
        return BookRepository.findById(id);
    }

    // READ (uno por titulo)
    public List<Book> getBookByTitulo(String titulo) {
        return BookRepository.findByTituloContainingIgnoreCase(titulo);
    }

    // DELETE
    public void deleteBook(Long id) {
        BookRepository.deleteById(id);
    }

    // UPDATE
    public Book updateBook(Long id, Book newBookData) {
        Book book = BookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        book.setTitulo(newBookData.getTitulo());
        book.setCodigo(newBookData.getCodigo());

        return BookRepository.save(book);
    }



}
