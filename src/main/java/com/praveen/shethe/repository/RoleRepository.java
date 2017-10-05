package com.praveen.shethe.repository;

import com.praveen.shethe.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Praveenkumar on 7/16/2017.
 * RoleRepository Interface extends generic CRUD operations on a repository for a specific type.
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
