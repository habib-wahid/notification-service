package com.example.notification_consumer.service.Implementation;

import com.example.notification_consumer.model.Email;
import com.example.notification_consumer.model.NotificationTemplate;
import com.example.notification_consumer.model.NotificationType;
import com.example.notification_consumer.model.User;
import com.example.notification_consumer.service.ChannelHandler;
import com.example.notification_consumer.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component("EMAIL")
public class EmailHandler implements ChannelHandler {

    private final EmailService emailService;

    @Override
    public String getChannelName() {
        return "EMAIL";
    }

    @Override
    public void handle(User user, NotificationType type, NotificationTemplate tpl, String content) throws Exception {
        List<Email> emails = emailService.findAllByUserId(user.getId());
        for (Email e : emails) {
            emailService.sendEmail(content,e.getEmailAddress(), tpl.getSubject());
        }
    }
}
