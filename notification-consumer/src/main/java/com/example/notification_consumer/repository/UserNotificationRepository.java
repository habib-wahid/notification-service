package com.example.notification_consumer.repository;

import com.example.notification_consumer.model.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserNotificationRepository extends JpaRepository<UserNotification, Long> {
}
