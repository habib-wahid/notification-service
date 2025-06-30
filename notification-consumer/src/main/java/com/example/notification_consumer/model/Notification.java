package com.example.notification_consumer.model;

import com.example.notification_consumer.enumTypes.NOTIFICATION_STATUS;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ntotification")
public class Notification extends BaseEntity{

    @Column(nullable=false)
    private String title;

    @Column(nullable=false, columnDefinition="TEXT")
    private String message;

    @ManyToOne
    @JoinColumn(name="type_id", nullable=false)
    private NotificationType type;

    private LocalDateTime scheduledAt;

    @Enumerated(EnumType.STRING)
    private NOTIFICATION_STATUS status;

    private LocalDateTime sentAt;

    @OneToMany(mappedBy="notification", cascade=CascadeType.ALL)
    private List<UserNotification> userNotifications;

    @OneToMany(mappedBy="notification", cascade=CascadeType.ALL)
    private List<NotificationRetryLog> retryLogs;
}
