package com.praveen.shethe.controller.restexceptionhandler;

import java.util.Date;

/**
 * Created by Praveenkumar on 3/8/2017.
 */
public class ErrorResponse {
    private String errorTitle;
    private int httpStatus;
    private String errorDetailsForUser;
    private String errorDetailsForDeveloper;
    private Date errorTimestamp;

    public ErrorResponse() {
        this.errorTimestamp = new Date();
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public ErrorResponse setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
        return this;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public ErrorResponse setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public String getErrorDetailsForUser() {
        return errorDetailsForUser;
    }

    public String getErrorDetailsForDeveloper() {
        return errorDetailsForDeveloper;
    }

    public Date getErrorTimestamp() {
        return errorTimestamp;
    }

    public ErrorResponse setException(final Exception exception) {
        this.errorDetailsForUser = exception.getMessage();
        this.errorDetailsForDeveloper = exception.toString();
        return this;
    }
}