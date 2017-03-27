package com.praveen.shethe.repository;

import com.praveen.shethe.model.Upayogakarta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Praveenkumar on 3/7/2017.
 */
@Repository
public interface UpayogakartaRepository extends CrudRepository<Upayogakarta,Long>{

    Upayogakarta findByUsername(String username);
}
