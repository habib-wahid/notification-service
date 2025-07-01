package com.example.notification_consumer.repository;

import com.example.notification_consumer.model.NotificationRetryLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRetryLogRepository extends JpaRepository<NotificationRetryLog, Long> {
}
