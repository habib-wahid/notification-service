package com.example.notification_consumer.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "user_device_token")
@Entity
@Getter
@Setter
public class UserDeviceToken extends BaseEntity{

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @Column(nullable=false, columnDefinition="TEXT")
    private String token;

    private String platform;
    private Boolean status;
    private Long appId;


}
