package com.praveen.shethe.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;

/**
 * Created by plank-praveen on 12/28/2016.
 */
public class UpayogakartaAuthenticationException extends AuthenticationException{
    private static final Logger logger = LoggerFactory.getLogger(UpayogakartaAuthenticationException.class);

    /**
     * Constructs an {@code AuthenticationException} with the specified message and root
     * cause.
     *
     * @param message the detail message
     * @param throwable   the root cause
     */
    public UpayogakartaAuthenticationException(String message, Throwable throwable) {
        super(message, throwable);
        logException(message);
    }

    /**
     * Constructs an {@code AuthenticationException} with the specified message and no
     * root cause.
     *
     * @param msg the detail message
     */
    public UpayogakartaAuthenticationException(String msg) {
        super(msg);
        logException(msg);
    }

    private static void logException(final String logMessage) {
        logger.info(logMessage);
    }
}
