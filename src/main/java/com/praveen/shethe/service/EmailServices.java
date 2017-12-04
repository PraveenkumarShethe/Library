package com.praveen.shethe.service;

import com.praveen.shethe.service.serviceimpl.EmailServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Created by Praveenkumar on 4/12/2017.
 * Email Services for sending e-mail to user
 */
@Service
public class EmailServices implements EmailServiceImpl{

    @Override
    public void sendEmail(String toAddress, String fromAddress, String body, String subjectLine) {

    }
}
