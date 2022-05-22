package com.authorandbooks.authorAndBooks.service;

import com.authorandbooks.authorAndBooks.enums.EntityType;
import com.authorandbooks.authorAndBooks.errors.*;
import com.authorandbooks.authorAndBooks.model.Author;
import com.authorandbooks.authorAndBooks.model.Book;
import com.authorandbooks.authorAndBooks.repo.AuthorRepository;
import com.authorandbooks.authorAndBooks.repo.BookRepository;
import com.authorandbooks.authorAndBooks.util.OptionalToEntityConvertorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LibraryService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    //------------------------------------------Creating Author---------------------------------------------------

    public Author createAuthor(final Author author) throws LibraryException {

        //Checking if the specific Author is already exist
        for (Author a : authorRepository.findAll()) {
            if (a.getName().equals(author.getName())) {
                throw new EntityAlreadyExistsException(EntityType.AUTHOR, Constraint.ENTITY_ALREADY_EXISTS);
            }
        }
        //Creating the Author entity
        Author newAuthor = authorRepository.save(author);
        log.info("The new Author: " + author.getName() + " has been created successfully!");

        return newAuthor;
    }


    //------------------------------------------Deleting an author---------------------------------------------------

    public void deleteAuthor(final Long authorId) throws LibraryException {

        //Checking if Author is not exists
        if (!authorRepository.existsById(authorId)) {
            throw new EntityNotExistException(EntityType.AUTHOR, Constraint.ENTITY_NOT_EXISTS);
        }

        //Deleting the specific Author
        log.info("The selected Author has been deleted!");
        authorRepository.deleteById(authorId);
    }

    //------------------------------------------Getting all books----------------------------------------------

    public Set<Book> getAllBooks() throws LibraryException {

        //Setting and adding all companies in a list of companies
        Set<Book> booksList = new HashSet<>(bookRepository.findAll());

        //Checking if companies are not exist
        if (booksList == null) {
            throw new EntityNotExistException(EntityType.BOOK, Constraint.ENTITY_NOT_EXISTS);
        }

        return booksList;
    }

    //------------------------------------------Getting an author----------------------------------------------

    public Author getAuthor(final Long authorId) throws LibraryException {

        //Setting a specific Author to a variable
        Author author = OptionalToEntityConvertorUtil.optionalAuthor(authorRepository.findById(authorId));

        //Checking if the Author is not exist
        if (author == null) {
            throw new EntityNotExistException(EntityType.AUTHOR, Constraint.ENTITY_NOT_EXISTS);
        }

        return author;
    }

    //------------------------------------------Getting books by specific year-------------------------------------

    public Set<Book> getBooksByYear(final int startYear, final int endYear) throws LibraryException {

        //Setting a set of all existing books
        Set<Book> books = getAllBooks();

        //Checking if the books are not exists
        if (books == null) {
            throw new EntityNotExistException(EntityType.BOOK, Constraint.ENTITY_NOT_EXISTS);
        }

        //Checking if the books are not exists
        if (startYear > endYear) {
            throw new InvalidDateInsertion(Constraint.INVALID_DATE);
        }

        //Checking for each book if it is according to inserted years
        return books.
                stream().
                filter(book -> book.getYear() >= startYear && book.getYear() <= endYear).
                collect(Collectors.toSet());
    }

    //------------------------------------------Getting avg years of books----------------------------------------

    public double getYearBooksAvg() throws LibraryException {

        double sumOfYears = 0;
        double avg;

        //Setting a set of all existing books
        Set<Book> books = getAllBooks();

        //Checking if the books are not exists
        if (books == null) {
            throw new EntityNotExistException(EntityType.BOOK, Constraint.ENTITY_NOT_EXISTS);
        }

        //Checking for each book if it is according to inserted years
        for (Book book : books) {
            sumOfYears += book.getYear();
        }

        avg = sumOfYears / books.size();

        return avg;
    }

    //------------------------------------------Getting avg years of specific author books------------------------

    public double getAuthorYearBooksAvg(final Long authorId) throws LibraryException {

        double sumOfYears = 0;
        double avg;

        Author author = OptionalToEntityConvertorUtil.optionalAuthor(authorRepository.findById(authorId));

        //Setting a set of all existing books of specific author
        Set<Book> authorBooks;

        authorBooks = author.getBooks();

        //Checking if the books are not exists
        if (authorBooks == null) {
            throw new EntityNotExistException(EntityType.BOOK, Constraint.ENTITY_NOT_EXISTS);
        }

        //Checking for each book if it is according to inserted years
        for (Book book : authorBooks) {
            sumOfYears += book.getYear();
        }

        avg = sumOfYears / authorBooks.size();

        return avg;
    }

    //------------------------------------------Getting all author books-------------------------------------------

    public Set<Book> getAuthorBooks(final Long authorId) throws LibraryException {

        Author author = OptionalToEntityConvertorUtil.optionalAuthor(authorRepository.findById(authorId));

        //Setting a set of all existing books of specific author
        Set<Book> authorBooks;

        authorBooks = author.getBooks();

        //Checking if the books are not exists
        if (authorBooks == null) {
            throw new EntityNotExistException(EntityType.BOOK, Constraint.ENTITY_NOT_EXISTS);
        }

        return authorBooks;
    }
}
