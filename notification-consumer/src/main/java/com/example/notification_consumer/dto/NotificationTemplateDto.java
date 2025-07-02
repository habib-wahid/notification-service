package com.example.notification_consumer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationTemplateDto extends BaseDto{
    private String name;
    private String subject;
    private String content;
    private String css;
    private Long notificationTypeId;
    private Long notificationChannelId;
}
