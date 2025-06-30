package com.example.notification_consumer.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contact")
@Getter
@Setter
public class Contact extends BaseEntity{

    @Column(name="phone_number", nullable=false, unique=true)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
}
