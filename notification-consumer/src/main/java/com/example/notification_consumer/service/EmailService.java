package com.example.notification_consumer.service;

import com.example.notification_consumer.model.Email;
import jakarta.mail.MessagingException;

import java.util.List;

public interface EmailService {
    void sendEmail(String htmlContent,String emailAddress, String Subject) throws MessagingException;
    List<Email> findAllByUserId(Long userId);
}
