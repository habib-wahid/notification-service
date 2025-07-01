package com.example.notification_consumer.service;

import com.example.notification_consumer.dto.DeleteResponseDto;
import com.example.notification_consumer.dto.NotificationRetryLogDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface NotificationRetryLogService {
    NotificationRetryLogDto create(NotificationRetryLogDto log);

    NotificationRetryLogDto find(Long id);

    Page<NotificationRetryLogDto> findAll(int page, int size, Sort.Direction direction, String sortBy);

    NotificationRetryLogDto update(Long id, NotificationRetryLogDto log);

    DeleteResponseDto delete(Long id);
}
