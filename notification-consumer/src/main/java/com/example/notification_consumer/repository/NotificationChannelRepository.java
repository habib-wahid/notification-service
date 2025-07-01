package com.example.notification_consumer.repository;

import com.example.notification_consumer.model.NotificationChannel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationChannelRepository extends JpaRepository<NotificationChannel, Long> {
}
