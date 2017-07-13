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
 */
@RestController
@RequestMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(Transactional.TxType.NEVER)
    public Iterable<Book> getAllBooks(Principal principal) {
        return bookRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(Transactional.TxType.NEVER)
    public Book getBook(Principal principal,@PathVariable("id") Long id ) {
        return bookRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void insertNewBook(Principal principal,@RequestBody Book book){
        bookRepository.save(book);
    }

}
