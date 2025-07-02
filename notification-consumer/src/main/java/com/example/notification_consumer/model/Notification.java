package com.example.notification_consumer.model;

import com.example.notification_consumer.enumTypes.NotificationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "notification")
@Getter
@Setter
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
    private NotificationStatus status;

    private LocalDateTime sentAt;

    @OneToMany(mappedBy="notification", cascade=CascadeType.ALL)
    private List<UserNotification> userNotifications;

}
