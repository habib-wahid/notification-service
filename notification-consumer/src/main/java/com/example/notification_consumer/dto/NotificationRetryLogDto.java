package com.example.notification_consumer.dto;

import java.time.LocalDateTime;

public class NotificationRetryLogDto extends BaseDto{
    private Long userNotificationId;
    private LocalDateTime attemptedAt;
    private Boolean success;
    private String errorMessage;
    private Integer retryCount;
    private Integer maxRetries;
    private LocalDateTime sentAt;
}
