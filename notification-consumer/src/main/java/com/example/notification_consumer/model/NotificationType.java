package com.example.notification_consumer.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "notification_type")
@Getter
@Setter
public class NotificationType extends BaseEntity{

    @Column(nullable=false, unique=true)
    private String name;

    private String description;

    @OneToMany(mappedBy="type", cascade= CascadeType.ALL)
    private List<Notification> notifications;

    @OneToMany(mappedBy="type", cascade=CascadeType.ALL)
    private List<NotificationTemplate> templates;

}
