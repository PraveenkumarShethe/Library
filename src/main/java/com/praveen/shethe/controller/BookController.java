package com.praveen.shethe.controller;

import com.praveen.shethe.model.Book;
import com.praveen.shethe.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;

/**
 * Created by Praveenkumar on 3/7/2017.
 * Controller class to handle REST API calles to /books/**
 */
@RestController
@RequestMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    /**
     * @return An iterable of the list of Books without filter
     * Http.ok will be returned{@code 200 OK}.
     * Http.NOT_FOUND will be returned if not found {@code 404 Not Found}.
     */
    //Todo Need to add doc for principle
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(Transactional.TxType.NEVER)
    public Iterable<Book> getAllBooks(Principal principal) {
        return bookRepository.findAll();
    }

    /**
     * @param id The record id of the Book that will be queried
     * @return THe Book object
     * Http.ok will be returned{@code 200 OK}.
     * Http.NOT_FOUND will be returned if not found {@code 404 Not Found}.
     */
    //Todo Need to add doc for principle
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(Transactional.TxType.NEVER)
    public Book getBook(Principal principal,@PathVariable("id") Long id ) {
        return bookRepository.findOne(id);
    }

    /**
     * Add a new Book to the Hotel database.
     * @param book The Hotel object to be inserted
     * {@code 201 Created}. for creating an object
     * {@code 400 Bad Request}. for all other requests
     */
    //Todo Need to add doc for principle
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void insertNewBook(Principal principal,@RequestBody Book book){
        bookRepository.save(book);
    }

}
