package com.inforcol.seguros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inforcol.seguros.model.Author;

import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByNameContainingIgnoreCase(String name);

}
