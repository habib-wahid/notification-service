package com.example.notification_consumer.service.Implementation;

import com.example.notification_consumer.model.*;
import com.example.notification_consumer.service.NotificationService;
import com.example.notification_consumer.service.NotificationTemplateService;
import com.example.notification_consumer.service.NotificationTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProcessService {

    private final NotificationService notificationService;
    private final UserService userService;
    private final NotificationTemplateService notificationTemplateService;
    private final UserPreferenceService userPreferenceService;
    private final NotificationTypeService notificationTypeService;

    public void processNotification(NotificationEvent event) {
        User user = userService.findById(event.getUserId());
        List<UserPreference> userPreference = userPreferenceService.getUserPreference(event.getUserId());
        NotificationType type = notificationTypeService.findByName(event.getNotificationType());
        if (!userPreference.isEmpty()) {
            List<NotificationChannel> notificationChannels = userPreference.stream()
                    .map(UserPreference::getChannel)
                    .toList();

            List<NotificationTemplate> templates = new ArrayList<>();
            for (NotificationChannel channel : notificationChannels) {
                templates.add(notificationTemplateService.findByChannelIdAndTypeId(channel.getId(), type.getId()));
            }
            log.info("Processing notification for user: {}, type: {}, channels: {}", user.getId(), type.getName(), notificationChannels);

            Map<String, Object> templateParams = buildTemplateParams(user, type);

        }
    }


    private Map<String, Object> buildTemplateParams( User user, NotificationType notificationType) {
        Map<String, Object> templateParams = new HashMap<>();

        templateParams.put("userName", user.getUserName());
        templateParams.put("eventType", notificationType.getName());

        return templateParams;
    }


}
