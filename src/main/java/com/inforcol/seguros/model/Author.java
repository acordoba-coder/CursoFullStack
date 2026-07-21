package com.inforcol.seguros.model;

import java.util.List;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String country;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_detail_id",
            referencedColumnName = "id")
    private AuthorDetail authorDetail;

    @OneToMany(mappedBy = "author",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Book> books;

}
