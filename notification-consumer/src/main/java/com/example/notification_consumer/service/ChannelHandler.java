package com.example.notification_consumer.service;

import com.example.notification_consumer.model.NotificationTemplate;
import com.example.notification_consumer.model.NotificationType;
import com.example.notification_consumer.model.User;

public interface ChannelHandler {
    String getChannelName();
    void handle(User user, NotificationType type, NotificationTemplate tpl, String content) throws Exception;
}
