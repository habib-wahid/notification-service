package com.example.notification_consumer.service;

import com.example.notification_consumer.dto.DeleteResponseDto;
import com.example.notification_consumer.dto.NotificationTypeDto;
import com.example.notification_consumer.model.NotificationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface NotificationTypeService {
    NotificationType findById(Long notificationTypeId);

    NotificationTypeDto create(NotificationTypeDto dto);

    NotificationTypeDto find(Long id);

    NotificationTypeDto update(Long id, NotificationTypeDto dto);

    DeleteResponseDto delete(Long id);

    Page<NotificationTypeDto> findAll(int page, int size, Sort.Direction direction, String sortBy);

    NotificationType findByName(String notificationType);
}
