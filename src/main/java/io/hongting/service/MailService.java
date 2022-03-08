package io.hongting.service;

import io.hongting.entity.Comment;

import javax.mail.MessagingException;

/**
 * @author hongting
 * @create 2022 02 09 4:03 PM
 */
public interface MailService {

    void sendTemplateMail (Comment comment) throws MessagingException;


    void sendHtmlMail(String to, String subject, String content)  throws MessagingException;;
}
