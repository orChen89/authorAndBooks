package com.authorandbooks.authorAndBooks.model;

import lombok.*;

import javax.persistence.*;

@Data
@ToString
@Entity
@Table(name = "book")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @Column(name = "year", nullable = false)
    private int year;
}
