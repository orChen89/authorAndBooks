package com.authorandbooks.authorAndBooks.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@ToString
@Entity
@Table(name = "author")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.ALL})
    private Set<Book> books;
}
