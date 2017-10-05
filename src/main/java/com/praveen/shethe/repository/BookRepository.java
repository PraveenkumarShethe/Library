package com.praveen.shethe.repository;

import com.praveen.shethe.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Praveenkumar on 3/7/2017.
 * BookRepository Interface extends generic CRUD operations on a repository for a specific type.
 */
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
