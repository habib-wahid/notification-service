package com.example.notification_consumer.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {

    @Column(name="user_name", nullable=false, unique=true)
    private String userName;

    @Column(nullable=false)
    private String password;

    @Column(name="is_active", nullable=false)
    private Boolean active = true;

    private String name;
    private String firstName;
    private String lastName;
    private String gender;

    @OneToMany(mappedBy="user", cascade= CascadeType.ALL)
    private List<Email> emails;

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<Contact> contacts;

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<UserDeviceToken> deviceTokens;

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<UserPreference> preferences;

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<UserNotification> deliveries;
}

