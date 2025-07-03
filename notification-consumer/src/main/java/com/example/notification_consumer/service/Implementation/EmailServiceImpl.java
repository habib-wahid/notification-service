package com.example.notification_consumer.service.Implementation;

import com.example.notification_consumer.exception.NotFoundException;
import com.example.notification_consumer.model.Email;
import com.example.notification_consumer.repository.EmailRepository;
import com.example.notification_consumer.service.EmailService;
import com.example.notification_consumer.service.NotificationTemplateService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;
    private final JavaMailSender mailSender;

    public Email findById(Long id) {
        return emailRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Email not found with id: " + id));
    }

    public List<Email> findAllByUserId(Long userId) {
        return emailRepository.findAllByUserId(userId);
    }


    public void sendEmail(String htmlContent, String emailAddress) throws MessagingException {
        log.info("Sending email...");

        try {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setTo(emailAddress);
        helper.setSubject("User Credentials");
        helper.setText(htmlContent, true);
        mailSender.send(message);

       // mailSender.send(mimeMessage);
        log.info("Email sent successfully");
        } catch (MessagingException e) {
            log.error("Error sending email: {}", e.getMessage());
            throw new MessagingException("Failed to send email", e);
        }
    }

}
