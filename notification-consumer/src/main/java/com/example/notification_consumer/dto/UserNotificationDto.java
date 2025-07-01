package com.example.notification_consumer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotificationDto extends BaseDto{
    private Long userId;
    private Long notificationId;
    private Long channelId;
}
