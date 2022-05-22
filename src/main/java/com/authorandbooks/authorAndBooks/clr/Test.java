package com.authorandbooks.authorAndBooks.clr;

import com.authorandbooks.authorAndBooks.model.Author;
import com.authorandbooks.authorAndBooks.model.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;

import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class Test implements CommandLineRunner {

    private final RestTemplate restTemplate;

    @Override
    public void run(String... args) throws Exception {


        System.out.println(authorCreation());
        System.out.println(authorDeletion());
        System.out.println(getAuthor());
        System.out.println(getAllBooks());
        System.out.println(getBooksByYear());
        System.out.println(getYearBooksAvg());
        System.out.println(getAuthorYearBooksAvg());
    }

    public boolean authorCreation() {

        Set<Book> authorBooks = new HashSet<>();

        Book book1 = Book.builder().
                name("Tolahat").
                year(2017).
                build();

        Book book2 = Book.builder().
                name("Kashuah").
                year(2022).
                build();

        Book b4= Book.builder().
                name("Mangal").
                year(2010).
                build();

        authorBooks.add(book1);
        authorBooks.add(book2);
        authorBooks.add(b4);

        //Setting an author in a variable with its specific books
        Author newAuthor =
                Author.builder().
                        name("Shaul").
                        books(authorBooks).
                        build();

        try {
            //Setting a response entity of author and activating the controller post method
            ResponseEntity<Author> authorEntity = restTemplate.
                    postForEntity(TestUrlConstants.POST_AUTHOR_URL, newAuthor, Author.class);

            //Checking the author body is not null
            Author author = authorEntity.getBody();
            System.out.println(author);
            log.info("Test Passed!");
            return author != null;
          //Catching all exceptions
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public boolean authorDeletion() {

        try {
            //Activating the controller delete method on url
            restTemplate.delete(TestUrlConstants.AUTHOR_DELETION_URL + "1");
            log.info("Test Passed!");
            log.info("The selected author has been deleted!");
            return true;
            //Catching all exceptions
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public boolean getAuthor() {

        try {
            //Setting a response entity of author and activating the controller get method
            ResponseEntity<Author> authorEntity = restTemplate.
                    getForEntity(TestUrlConstants.GET_AUTHOR_URL + "1", Author.class);

            //Getting an author body
            Author author = authorEntity.getBody();
            System.out.println(author);
            log.info( "Test Passed!");
            return author != null;
         //Catching all exceptions
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public boolean getAllBooks() {

        try {
            //Setting a response entity of Books and activating the controller get all method
            ResponseEntity<Book[]> allBooks = restTemplate.
                    getForEntity(TestUrlConstants.GETTING_ALL_BOOKS_URL, Book[].class);

            //Getting a customers list to an objects array body
            Book[] books = allBooks.getBody();

            assert books != null;
            //Placing the received body of books set in a set variable
            Set<Book> currentBooksList = (Set.of(books));
            System.out.println(currentBooksList);
            log.info("Test Passed!");
            return currentBooksList != null;
            //Catching all exceptions
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public boolean getBooksByYear() {

        try {
            //Setting a response entity of Books and activating the controller get all method
            ResponseEntity<Book[]> allBooks = restTemplate.
                    getForEntity("http://localhost:8080/library/booksByYears/2015/2020/", Book[].class);

            //Getting a customers list to an objects array body
            Book[] books = allBooks.getBody();

            assert books != null;
            //Placing the received body of books set in a set variable
            Set<Book> currentBooksList = (Set.of(books));
            System.out.println(currentBooksList);
            log.info("Test Passed!");
            return currentBooksList != null;
            //Catching all exceptions
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public boolean getYearBooksAvg() {

        try {
            //Setting a response entity of double and activating the controller get method
            ResponseEntity<Double> avgEntity = restTemplate.
                    getForEntity(TestUrlConstants.GET_AVG_BOOKS_YEARS_URL, Double.class);

            //Getting a double body
            Double avg = avgEntity.getBody();
            System.out.println(avg);
            log.info( "Test Passed!");
            return avg != null;
          //Catching all exceptions
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public boolean getAuthorYearBooksAvg() {

        try {
            //Setting a response entity of double and activating the controller get method
            ResponseEntity<Double> avgEntity = restTemplate.
                    getForEntity("http://localhost:8080/library/authorAvgBooks/1", Double.class);

            //Getting a double body
            Double avg = avgEntity.getBody();
            System.out.println(avg);
            log.info( "Test Passed!");
            return avg != null;
          //Catching all exceptions
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}







