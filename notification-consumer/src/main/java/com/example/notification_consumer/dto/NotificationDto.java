package com.example.notification_consumer.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificationDto extends BaseDto{
    private String title;
    private String message;
    private Long typeId;
    private LocalDateTime scheduledAt;
    private String status;
    private LocalDateTime sentAt;
}
