package com.example.notification_consumer.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "email")
@Getter
@Setter
public class Email extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String emailAddress;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
