package com.inforcol.seguros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inforcol.seguros.model.Book;

public interface BookRepository extends JpaRepository <Book, Long> {

    List<Book> findByTituloContainingIgnoreCase(String titulo);
}
