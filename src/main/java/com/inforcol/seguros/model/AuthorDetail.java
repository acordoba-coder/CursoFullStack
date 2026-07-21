package com.inforcol.seguros.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "author_details")
@Data
public class AuthorDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String biography;

    private LocalDate birthDate;

    private String nationality;

    @OneToOne(mappedBy = "authorDetail")
    private Author author;
}
