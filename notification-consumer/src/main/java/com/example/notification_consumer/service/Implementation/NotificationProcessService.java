package com.example.notification_consumer.service.Implementation;

import com.example.notification_consumer.model.*;
import com.example.notification_consumer.service.EmailService;
import com.example.notification_consumer.service.NotificationService;
import com.example.notification_consumer.service.NotificationTemplateService;
import com.example.notification_consumer.service.NotificationTypeService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProcessService {

    private final NotificationService notificationService;
    private final UserService userService;
    private final NotificationTemplateService notificationTemplateService;
    private final UserPreferenceService userPreferenceService;
    private final NotificationTypeService notificationTypeService;
    private final SpringTemplateEngine templateEngine;
    private final EmailService emailService;


    public void processNotification(NotificationEvent event) throws MessagingException {
        log.info("Processing notification for event: {}", event);
        User user = userService.findById(event.getUserId());
        List<UserPreference> userPreference = userPreferenceService.getUserPreference(event.getUserId());
        NotificationType type = notificationTypeService.findByName(event.getNotificationType());
        if (!userPreference.isEmpty()) {
            List<NotificationChannel> notificationChannels = userPreference.stream()
                    .map(UserPreference::getChannel)
                    .toList();

            NotificationTemplate emailTemplate = null;
            for (NotificationChannel channel : notificationChannels) {
                if (channel.getName().equalsIgnoreCase("EMAIL")) {
                    emailTemplate = notificationTemplateService.findByChannelIdAndTypeId(channel.getId(), type.getId());
                }
            }
            log.info("Processing notification for user: {}, type: {}, channels: {}", user.getId(), type.getName(), notificationChannels);

            Map<String, Object> templateParams = buildTemplateParams(2L, event.getNotificationType());

            String htmlContent = buildTemplate(emailTemplate, templateParams);
            emailService.sendEmail(htmlContent);
        }
    }

    private Map<String, Object> buildTemplateParams(Long userId, String notificationType) {
        Map<String, Object> templateParams = new HashMap<>();

        templateParams.put("userName", userId);
        templateParams.put("eventType", notificationType);

        return templateParams;
    }


    private Map<String, Object> buildTemplateParams(User user, NotificationType notificationType) {
        Map<String, Object> templateParams = new HashMap<>();

        templateParams.put("userName", user.getUserName());
        templateParams.put("eventType", notificationType.getName());

        return templateParams;
    }

    private String buildTemplate(NotificationTemplate emailTemplate, Map<String, Object> templateParams) {
        log.info("Building email template: {}", emailTemplate);
        Context context = new Context();
        context.setVariables(templateParams);
        return templateEngine.process(emailTemplate.getContent(), context);
    }

}
