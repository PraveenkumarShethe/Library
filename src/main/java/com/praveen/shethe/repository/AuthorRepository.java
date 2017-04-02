package com.praveen.shethe.repository;

import com.praveen.shethe.model.Author;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Praveenkumar on 4/1/2017.
 */
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
