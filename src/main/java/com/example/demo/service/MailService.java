package com.example.demo.service;

import javax.mail.MessagingException;

public interface MailService {
    void sendSimpleMail(String to, String subject, String content) ;

    void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);

    void sendHtmlMail(String to, String subject, String content) throws  MessagingException;
}
