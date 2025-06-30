package com.example.notification_consumer.model;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "user_preference")
@Entity
@Getter
@Setter
public class UserPreference extends BaseEntity{
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="channel_id", nullable=false)
    private NotificationChannel channel;

}
