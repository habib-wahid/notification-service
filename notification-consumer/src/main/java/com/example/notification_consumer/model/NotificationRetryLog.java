package com.example.notification_consumer.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification_retry_log")
@Getter
@Setter
public class NotificationRetryLog extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_notification_id", nullable = false)
    private UserNotification userNotification;

    @Column(name = "attempted_at", updatable = false)
    private LocalDateTime attemptedAt;

    @Column(nullable = false)
    private Boolean success;

    @Column(columnDefinition = "TEXT")
    private String errorMessage;

    private Integer retryCount = 0;
    private Integer maxRetries = 3;
    private LocalDateTime sentAt;
}

