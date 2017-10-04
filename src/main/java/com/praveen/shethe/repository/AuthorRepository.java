package com.praveen.shethe.repository;

import com.praveen.shethe.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Praveenkumar on 4/1/2017.
 * AuthorRepository Interface extends generic CRUD operations on a repository for a specific type.
 */
@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
