package com.example.notification_consumer.service;

import com.example.notification_consumer.dto.EmailDto;
import jakarta.mail.MessagingException;

public interface EmailService {
    void sendEmail(String htmlContent) throws MessagingException;
}
