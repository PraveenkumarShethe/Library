package com.praveen.shethe.repository;

import com.praveen.shethe.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Praveenkumar on 4/1/2017.
 */
@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
