package com.praveen.shethe.controller;

import com.praveen.shethe.controller.restexceptionhandler.LibraryResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * Created by Praveenkumar on 3/8/2017.
 * RestPreconditions for all null checks for controller layer checks
 */
public class RestApiPreconditions {

    private static final Logger logger = LoggerFactory.getLogger(RestApiPreconditions.class);

    public static <T> T checkFound(final T resource) throws LibraryResourceNotFoundException{

        if (resource == null
                || (resource instanceof Collection
                && ((Collection) resource).isEmpty())
                || (resource instanceof Iterable
                && !((Iterable) resource).iterator().hasNext())) {
            throw new LibraryResourceNotFoundException();
        }
        return resource;
    }
}
