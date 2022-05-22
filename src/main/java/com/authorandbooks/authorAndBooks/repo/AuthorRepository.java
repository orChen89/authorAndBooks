package com.authorandbooks.authorAndBooks.repo;

import com.authorandbooks.authorAndBooks.model.Author;
import com.authorandbooks.authorAndBooks.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository <Author, Long> {


}
