package com.example.notification_consumer.dto;

import java.time.LocalDateTime;
import java.util.List;

public class NotificationDto extends BaseDto{
    private String title;
    private String message;
    private Long typeId;
    private LocalDateTime scheduledAt;
    private String status;
    private LocalDateTime sentAt;
    private List<Long> userNotificationIds;
    private List<Long> retryLogIds;
}
