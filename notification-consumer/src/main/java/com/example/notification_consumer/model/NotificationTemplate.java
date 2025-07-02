package com.example.notification_consumer.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "notification_template")
@Getter
@Setter
public class NotificationTemplate extends BaseEntity{

    @Column(nullable=false)
    private String name;

    private String subject;

    @Column(nullable=false, columnDefinition="TEXT")
    private String content;

    @Column(columnDefinition="TEXT")
    private String css;

    @ManyToOne
    @JoinColumn(name="type_id", nullable=false)
    private NotificationType notificationType;

    @ManyToOne
    @JoinColumn(name="channel_id", nullable=false)
    private NotificationChannel notificationChannel;
}
