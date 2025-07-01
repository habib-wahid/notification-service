package com.example.notification_consumer.repository;

import com.example.notification_consumer.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
