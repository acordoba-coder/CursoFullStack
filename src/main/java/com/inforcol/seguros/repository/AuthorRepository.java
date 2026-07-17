package com.inforcol.seguros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inforcol.seguros.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
