package com.praveen.shethe.controller.restexceptionhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Praveenkumar on 3/8/2017.
 */

/**
 * The class {@code Exception} and its subclasses are a form of
 * {@code Throwable} that indicates conditions that a reasonable
 * application might want to catch.
 *
 * <p>The class {@code Exception} and any subclasses that are not also
 * subclasses of {@link RuntimeException} are <em>checked
 * exceptions</em>.  Checked exceptions need to be declared in a
 * method or constructor's {@code throws} clause if they can be thrown
 * by the execution of the method or constructor and propagate outside
 * the method or constructor boundary.
 *
 * @author  Frank Yellin
 * @see     java.lang.Error
 * @jls 11.2 Compile-Time Checking of Exceptions
 * @since   JDK1.0
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

