package com.example.notification_consumer.service;

import com.example.notification_consumer.dto.DeleteResponseDto;
import com.example.notification_consumer.dto.NotificationDto;
import com.example.notification_consumer.model.Notification;
import org.springframework.data.domain.Page;

public interface NotificationService {

    NotificationDto create(NotificationDto notificationDto);
    NotificationDto update(NotificationDto notificationDto);
    NotificationDto find(Long id);
    Notification findById(Long id);
    DeleteResponseDto delete(Long id);
    Page<NotificationDto> findAll(int page, int size, String sortBy, String sortDirection);
}
