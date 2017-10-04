package com.praveen.shethe.controller.restexceptionhandler;


import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.security.cert.CertificateException;
import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Praveenkumar on 3/8/2017.
 */


/**
 * A convenient base class for {@link ControllerAdvice @ControllerAdvice} classes
 * that wish to provide centralized exception handling across all
 * {@code @RequestMapping} methods through {@code @ExceptionHandler} methods.
 *
 * <p>This base class provides an {@code @ExceptionHandler} method for handling
 * internal Spring MVC exceptions. This method returns a {@code ResponseEntity}
 * for writing to the response with a {@link HttpMessageConverter message converter}.
 * in contrast to
 * {@link org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver
 * DefaultHandlerExceptionResolver} which returns a
 * {@link org.springframework.web.servlet.ModelAndView ModelAndView}.
 *
 * <p>If there is no need to write error content to the response body, or when
 * using view resolution (e.g., via {@code ContentNegotiatingViewResolver}),
 * then {@code DefaultHandlerExceptionResolver} is good enough.
 *
 * <p>Note that in order for an {@code @ControllerAdvice} sub-class to be
 * detected, {@link ExceptionHandlerExceptionResolver} must be configured.
 *
 * @author Rossen Stoyanchev
 * @since 3.2
 * @see #handleException(Exception, WebRequest)
 * @see org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver
 */

@ControllerAdvice
public class LibraryRestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public LibraryRestResponseEntityExceptionHandler() {
        super();
    }


    /**
     * Provides handling for standard Spring MVC exceptions.
     * @param ex the target exception
     * @param request the current request
     */
    
    @ExceptionHandler(value = {
            ConstraintViolationException.class,
            IllegalArgumentException.class,
            DataIntegrityViolationException.class,
            CertificateException.class,
            AuthenticationException.class,
            EntityNotFoundException.class,
            InvalidDataAccessApiUsageException.class,
            DataAccessException.class,
            NoSuchAlgorithmException.class,
            KeyStoreException.class,
            IOException.class,
    })
    public final ResponseEntity<Object> handleLibraryException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        if (ex instanceof ConstraintViolationException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            return handleConstraintViolationException((ConstraintViolationException) ex,
                    headers, status, request);
        } else if (ex instanceof IllegalArgumentException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            return handleIllegalArgumentException((IllegalArgumentException) ex,
                    headers, status, request);
        } else if (ex instanceof DataIntegrityViolationException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            return handleDataIntegrityViolationException((DataIntegrityViolationException) ex,
                    headers, status, request);
        } else if (ex instanceof CertificateException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            return handleCertificateException((CertificateException) ex,
                    headers, status, request);
        } else if (ex instanceof AuthenticationException) {
            HttpStatus status = HttpStatus.UNAUTHORIZED;
            return handleAuthenticationException((AuthenticationException) ex,
                    headers, status, request);
        } else if (ex instanceof EntityNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            return handleEntityNotFoundException((EntityNotFoundException) ex,
                    headers, status, request);
        } else if (ex instanceof InvalidDataAccessApiUsageException) {
            HttpStatus status = HttpStatus.CONFLICT;
            return handleInvalidDataAccessApiUsageException((InvalidDataAccessApiUsageException) ex, headers, status, request);
        } else if (ex instanceof DataAccessException) {
            HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
            return handleDataAccessException((DataAccessException) ex, headers, status, request);
        } else if (ex instanceof NoSuchAlgorithmException) {
            HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
            return handleNoSuchAlgorithmException((NoSuchAlgorithmException) ex, headers, status, request);
        } else if (ex instanceof KeyStoreException) {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleKeyStoreException((KeyStoreException) ex, headers, status, request);
        } else if (ex instanceof IOException) {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleIOException((IOException) ex, headers, status, request);
        } else {
            logger.warn("Unknown exception type: " + ex.getClass().getName());
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, null, headers, status, request);
        }
    }

    // API
    // 400
    private ResponseEntity<Object> handleConstraintViolationException(final ConstraintViolationException ex,
                                                                      HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse bodyOfResponse = new ErrorResponse()
                .setErrorTitle("Constraint violation")
                .setException(ex);
        return handleExceptionInternal(ex, bodyOfResponse, headers, status, request);
    }

    private ResponseEntity<Object> handleIllegalArgumentException(final IllegalArgumentException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse bodyOfResponse = new ErrorResponse()
                .setErrorTitle("Incorrect or illegal arguments. Please check your URI and JSON inputs.")
                .setException(ex);
        return handleExceptionInternal(ex, bodyOfResponse, headers, status, request);
    }

    private ResponseEntity<Object> handleDataIntegrityViolationException(final DataIntegrityViolationException ex,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse bodyOfResponse = new ErrorResponse()
                .setErrorTitle("Data integrity violation")
                .setException(ex);
        return handleExceptionInternal(ex, bodyOfResponse, headers, status, request);
    }

    private ResponseEntity<Object> handleCertificateException(final CertificateException ex,
                                                              HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse bodyOfResponse = new ErrorResponse()
                .setErrorTitle("Error while processing the certificate")
                .setException(ex);
        return handleExceptionInternal(ex, bodyOfResponse, headers, status, request);
    }

    // 401
    private ResponseEntity<Object> handleAuthenticationException(final AuthenticationException ex,
                                                                 HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse bodyOfResponse = new ErrorResponse()
                .setErrorTitle("You are not authenticated")
                .setException(ex);
        return handleExceptionInternal(ex, bodyOfResponse, headers, status, request);
    }

    private ResponseEntity<Object> handleEntityNotFoundException(final EntityNotFoundException ex,
                                                                 HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse bodyOfResponse = new ErrorResponse()
                .setErrorTitle("Resource Not found.")
                .setException(ex);
        return handleExceptionInternal(ex, bodyOfResponse, headers, status, request);
    }

    // 409
    private ResponseEntity<Object> handleInvalidDataAccessApiUsageException(final InvalidDataAccessApiUsageException ex,
                                                                            HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse bodyOfResponse = new ErrorResponse()
                .setErrorTitle("Data conflicts due to invalid Data access APIs.")
                .setException(ex);
        return handleExceptionInternal(ex, bodyOfResponse, headers, status, request);
    }

    private ResponseEntity<Object> handleDataAccessException(final DataAccessException ex,
                                                             HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse bodyOfResponse = new ErrorResponse()
                .setErrorTitle("Data conflicts due to invalid Data access APIs.")
                .setException(ex);
        return handleExceptionInternal(ex, bodyOfResponse, headers, status, request);
    }

    private ResponseEntity<Object> handleNoSuchAlgorithmException(final NoSuchAlgorithmException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse bodyOfResponse = new ErrorResponse()
                .setErrorTitle("This certificate uses an algorithm unknown to the server.")
                .setException(ex);
        return handleExceptionInternal(ex, bodyOfResponse, headers, status, request);
    }

    // 500
    private ResponseEntity<Object> handleKeyStoreException(final KeyStoreException ex,
                                                           HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse bodyOfResponse = new ErrorResponse()
                .setErrorTitle("An internal server error encountered.")
                .setException(ex);
        return handleExceptionInternal(ex, bodyOfResponse, headers, status, request);
    }

    private ResponseEntity<Object> handleIOException(final IOException ex,
                                                     HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse bodyOfResponse = new ErrorResponse()
                .setErrorTitle("An internal server error encountered.")
                .setException(ex);
        return handleExceptionInternal(ex, bodyOfResponse, headers, status, request);
    }
}
