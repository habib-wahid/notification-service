package com.example.notification_consumer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChannelHandlerContext {
    private final User user;
    private final NotificationType type;
    private final NotificationChannel channel;
    private final NotificationTemplate template;
    private final String content;
}
