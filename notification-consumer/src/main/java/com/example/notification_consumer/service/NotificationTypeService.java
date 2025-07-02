package com.example.notification_consumer.service;

import com.example.notification_consumer.model.NotificationType;

public interface NotificationTypeService {
    NotificationType findById(Long notificationTypeId);

}
