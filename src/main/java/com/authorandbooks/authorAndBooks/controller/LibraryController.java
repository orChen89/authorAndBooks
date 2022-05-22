package com.authorandbooks.authorAndBooks.controller;

import com.authorandbooks.authorAndBooks.errors.LibraryException;
import com.authorandbooks.authorAndBooks.model.Author;
import com.authorandbooks.authorAndBooks.model.Book;
import com.authorandbooks.authorAndBooks.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("library")
@RestController
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Author createAuthor(@RequestBody final Author author) throws LibraryException {
       return libraryService.createAuthor(author);
    }

    @DeleteMapping("{authorId}")
    public void deleteAuthor(@PathVariable final long authorId) throws LibraryException {
        libraryService.deleteAuthor(authorId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getAuthor/{authorId}")
    public Author getAuthor(@PathVariable final long authorId) throws LibraryException {
        return libraryService.getAuthor(authorId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/books")
    public Set<Book> getAllBooks() throws LibraryException {
        return libraryService.getAllBooks();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("booksByYears/{startYear}/{endYear}")
    public Set<Book> getBooksByYear(@PathVariable(name = "startYear") final int startYear,
                                    @PathVariable(name = "endYear")
                                                final int endYear) throws LibraryException {
        return libraryService.getBooksByYear(startYear, endYear);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/avgBooks")
    public double getYearBooksAvg() throws LibraryException {
        return libraryService.getYearBooksAvg();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/authorAvgBooks/{authorId}")
    public double getAuthorYearBooksAvg(@PathVariable final long authorId) throws LibraryException {
        return libraryService.getAuthorYearBooksAvg(authorId);
    }
}
