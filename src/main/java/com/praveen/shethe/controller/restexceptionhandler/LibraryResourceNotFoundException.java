package com.praveen.shethe.controller.restexceptionhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Praveenkumar on 3/8/2017.
 */
public class LibraryResourceNotFoundException extends Exception{

    private static Logger logger = LoggerFactory.getLogger(LibraryResourceNotFoundException.class);

    public LibraryResourceNotFoundException(){super();}

    public LibraryResourceNotFoundException(final String message , final Throwable throwable){
        super(message , throwable);
        logger.info(message + "----------" + throwable.getCause());
    }

    public LibraryResourceNotFoundException(final String message){
        super(message);
        logger.info(message);
    }

    public LibraryResourceNotFoundException(final Throwable throwable){
        super(throwable);
        logger.info(throwable.getMessage());
        logger.info(throwable.getCause().toString());
    }
}

