package com.authorandbooks.authorAndBooks;

import com.authorandbooks.authorAndBooks.errors.LibraryException;
import com.authorandbooks.authorAndBooks.model.Author;
import com.authorandbooks.authorAndBooks.model.Book;
import com.authorandbooks.authorAndBooks.repo.AuthorRepository;
import com.authorandbooks.authorAndBooks.repo.BookRepository;
import com.authorandbooks.authorAndBooks.service.LibraryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AuthorAndBooksApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(AuthorAndBooksApplication.class, args);


	}
}
