package com.example.notification_consumer.repository;

import com.example.notification_consumer.model.NotificationTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationTemplateRepository extends JpaRepository<NotificationTemplate, Long> {
    Optional<NotificationTemplate> findByNotificationChannelIdAndNotificationTypeId(Long channelId, Long typeId);
}
