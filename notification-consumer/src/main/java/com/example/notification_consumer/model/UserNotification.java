package com.example.notification_consumer.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "user_notification")
@Getter
@Setter
public class UserNotification extends BaseEntity{

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="notification_id", nullable=false)
    private Notification notification;

    @ManyToOne
    @JoinColumn(name="channel_id", nullable=false)
    private NotificationChannel channel;

    @OneToMany(mappedBy="userNotification", cascade=CascadeType.ALL)
    private List<NotificationRetryLog> retryLogs;
}


