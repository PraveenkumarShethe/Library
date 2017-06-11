package com.praveen.shethe.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;

/**
 * Created by praveenkumar on 12/27/2016.
 */
public class LibraryUserAuthenticationException extends AuthenticationException {

    private static final Logger logger = LoggerFactory.getLogger(LibraryUserAuthenticationException.class);

    public LibraryUserAuthenticationException(String message, Throwable throwable) {
        super(message, throwable);
        logException(message);
    }

    public LibraryUserAuthenticationException(String msg) {
        super(msg);
        logException(msg);
    }

    private static void logException(final String logMessage) {
        logger.info(logMessage);
    }

}