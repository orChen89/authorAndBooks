package com.authorandbooks.authorAndBooks.util;

import com.authorandbooks.authorAndBooks.model.Author;
import com.authorandbooks.authorAndBooks.model.Book;

import java.util.Optional;

public class OptionalToEntityConvertorUtil {

    public static Author optionalAuthor(final Optional<Author> author) {

        return author.orElse(null);
    }

    public static Book optionalBook(final Optional<Book> book) {

        return book.orElse(null);
    }
}
