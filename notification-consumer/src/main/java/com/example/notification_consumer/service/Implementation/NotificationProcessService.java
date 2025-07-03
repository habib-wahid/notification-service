package com.example.notification_consumer.service.Implementation;

import com.example.notification_consumer.model.*;
import com.example.notification_consumer.service.EmailService;
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

    private final UserService userService;
    private final NotificationTemplateService notificationTemplateService;
    private final UserPreferenceService userPreferenceService;
    private final NotificationTypeService notificationTypeService;
    private final SpringTemplateEngine templateEngine;
    private final EmailService emailService;
    private final ChannelDispatchService channelDispatchService;


    public void processNotification(NotificationEvent event) throws Exception {
        log.info("Processing notification for event: {}", event);
        User user = userService.findById(event.getUserId());
        List<UserPreference> userPreference = userPreferenceService.getUserPreference(event.getUserId());
        NotificationType type = notificationTypeService.findByName(event.getNotificationType());

        if (userPreference.isEmpty()) {
            log.warn("No user preferences found for user: {}", user.getId());
            return;
        }

        log.info("User preferences found for user: {}, type: {}", user.getId(), type.getName());
        List<NotificationChannel> notificationChannels = userPreference.stream()
                .map(UserPreference::getChannel)
                .toList();

        for (NotificationChannel channel : notificationChannels) {
            NotificationTemplate template = notificationTemplateService.findByChannelIdAndTypeId(channel.getId(), type.getId());
            if (template == null) {
                log.warn("No template found for channel: {}, type: {}", channel.getName(), type.getName());
                continue;
            }
            Map<String, Object> templateParams = buildTemplateParams(user, type);
            String content = buildTemplate(template, templateParams);
            log.info("Dispatching notification for user: {}, type: {}, channel: {}", user.getId(), type.getName(), channel.getName());
            channelDispatchService.dispatch(new ChannelHandlerContext(user, type, channel, template, content));

        }
        log.info("Processing notification for user: {}, type: {}, channels: {}", user.getId(), type.getName(), notificationChannels);

    }


    private Map<String, Object> buildTemplateParams(User user, NotificationType notificationType) {
        Map<String, Object> templateParams = new HashMap<>();

        templateParams.put("userName", user.getUserName());
        templateParams.put("eventType", notificationType.getName());

        return templateParams;
    }

    private String buildTemplate(NotificationTemplate template, Map<String, Object> templateParams) {
        log.info("Building email template: {}", template.getName());
        Context context = new Context();
        context.setVariables(templateParams);
        return templateEngine.process(template.getContent(), context);
    }

}
