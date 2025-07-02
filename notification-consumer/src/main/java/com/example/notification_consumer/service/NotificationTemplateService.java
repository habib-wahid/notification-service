package com.example.notification_consumer.service;

import com.example.notification_consumer.dto.DeleteResponseDto;
import com.example.notification_consumer.dto.NotificationTemplateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface NotificationTemplateService {
    NotificationTemplateDto create(NotificationTemplateDto dto);

    NotificationTemplateDto find(Long id);

    Page<NotificationTemplateDto> findAll(int page, int size, Sort.Direction direction, String sortBy);

    NotificationTemplateDto update(Long id, NotificationTemplateDto dto);

    DeleteResponseDto delete(Long id);

}
