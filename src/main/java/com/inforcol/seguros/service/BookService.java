package com.inforcol.seguros.service;

import java.util.List;
import java.util.Optional;

import com.inforcol.seguros.dto.BookRequestDto;
import com.inforcol.seguros.dto.BookResponseDto;
import com.inforcol.seguros.dto.CategoryDto;
import com.inforcol.seguros.model.Author;
import com.inforcol.seguros.model.Category;
import com.inforcol.seguros.repository.AuthorRepository;
import com.inforcol.seguros.repository.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.inforcol.seguros.model.Book;
import com.inforcol.seguros.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookMapper bookMapper;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponseDto createBook(BookRequestDto dto) {
        // 1. Buscar al Autor
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado con ID: " + dto.getAuthorId()));

        // 2. Buscar las Categorías
        List<Category> categories = categoryRepository.findAllById(dto.getCategoryIds());

        // 3. Crear entidad y asignar relaciones
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setPrice(dto.getPrice());
        book.setAuthor(author);
        book.setCategories(categories);

        return bookMapper.toDto(bookRepository.save(book));
    }

    //UPDATE
    public BookResponseDto updateBook(Long id, BookRequestDto dto) {

    Book book = bookRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + id));

    Author author = authorRepository.findById(dto.getAuthorId())
            .orElseThrow(() -> new RuntimeException("Autor no encontrado con ID: " + dto.getAuthorId()));

    List<Category> categories = categoryRepository.findAllById(dto.getCategoryIds());

    book.setTitle(dto.getTitle());
    book.setIsbn(dto.getIsbn());
    book.setPrice(dto.getPrice());
    book.setAuthor(author);
    book.setCategories(categories);

    Book updatedBook = bookRepository.save(book);

    return convertToDTO(updatedBook);
    }

    // READ (todos)
    public List<BookResponseDto> listBooks() {
        return bookRepository.findAll()
            .stream()
            .map(this::convertToDTO)
            .toList();
    }

    // READ (uno por id)
    public Optional<BookResponseDto> getBookById(Long id) {
        return bookRepository.findById(id)
                .map(this::convertToDTO);
    }

    // READ (por título)
    public List<BookResponseDto> getBookByTitulo(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // DELETE
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("No existe un libro con el id: " + id);
        }

        bookRepository.deleteById(id);
    }



    private BookResponseDto convertToDTO(Book book) {

    BookResponseDto dto = new BookResponseDto();

    dto.setId(book.getId());
    dto.setTitle(book.getTitle());
    dto.setIsbn(book.getIsbn());
    dto.setPrice(book.getPrice());

    if (book.getAuthor() != null) {
        dto.setAuthorId(book.getAuthor().getId());
        dto.setAuthorName(book.getAuthor().getName());
    }

    dto.setCategories(
        book.getCategories()
            .stream()
            .map(category -> {
                CategoryDto categoryDto = new CategoryDto();
                categoryDto.setId(category.getId());
                categoryDto.setName(category.getName());
                return categoryDto;
            })
            .toList()
    );

    return dto;
    }




    


}
