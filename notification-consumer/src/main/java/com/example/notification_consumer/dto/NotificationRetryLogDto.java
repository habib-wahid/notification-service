package com.example.notification_consumer.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificationRetryLogDto extends BaseDto{
    private Long userNotificationId;
    private LocalDateTime attemptedAt;
    private Boolean success;
    private String errorMessage;
    private Integer retryCount;
    private Integer maxRetries;
    private LocalDateTime sentAt;
}
