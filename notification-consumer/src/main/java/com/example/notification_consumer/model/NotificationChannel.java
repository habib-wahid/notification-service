package com.example.notification_consumer.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "notification_channel")
@Getter
@Setter
public class NotificationChannel extends BaseEntity{

    @Column(nullable=false, unique=true)
    private String name;

    private String description;
    private Boolean isCustom = false;
    private String createdBy;

    @OneToMany(mappedBy="channel", cascade=CascadeType.ALL)
    private List<NotificationTemplate> templates;

    @OneToMany(mappedBy="channel", cascade=CascadeType.ALL)
    private List<UserPreference> preferences;

    @OneToMany(mappedBy="channel", cascade= CascadeType.ALL)
    private List<UserNotification> deliveries;
}
