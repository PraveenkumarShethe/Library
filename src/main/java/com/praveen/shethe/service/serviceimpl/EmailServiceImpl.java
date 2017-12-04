package com.praveen.shethe.service.serviceimpl;
/**
 * Created by Praveenkumar on 4/9/2017.
 *
 * EmailServiceImpl is an interface supports Email Services for sending e-mail to user
 */
public interface EmailServiceImpl {

    void sendEmail(String toAddress, String fromAddress, String body, String subjectLine);

}
