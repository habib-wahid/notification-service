package com.example.notification_consumer.repository;

import com.example.notification_consumer.model.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationTypeRepository extends JpaRepository<NotificationType, Long> {
}
