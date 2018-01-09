package com.praveen.shethe.service.serviceimpl;
/**
 * Created by Praveenkumar on 4/9/2017.
 *
 * EmailServiceImpl is an interface supports Email Services for sending e-mail to user
 */
public interface EmailServiceImpl {

    /**
     * The interfaces and classes for Java mail support in the Spring framework are organized as follows:

     MailSender interface: The top-level interface that provides basic functionality for sending simple emails
     JavaMailSender interface: the subinterface of the above MailSender.
     It supports MIME messages and is mostly used in conjunction with the MimeMessageHelper class for the creation of a MimeMessage.
     It’s recommended to use the MimeMessagePreparator mechanism with this interface
     JavaMailSenderImpl class: provides an implementation of the JavaMailSender interface. It supports the MimeMessage and SimpleMailMessage
     SimpleMailMessage class: used to create a simple mail message including the from, to, cc, subject and text fields
     MimeMessagePreparator interface: provides a callback interface for the preparation of MIME messages
     MimeMessageHelper class: helper class for the creation of MIME messages. It offers support for images,
     typical mail attachments and text content in an HTML layout

     * */

    void sendEmail(String toAddress, String fromAddress, String body, String subjectLine);

}
