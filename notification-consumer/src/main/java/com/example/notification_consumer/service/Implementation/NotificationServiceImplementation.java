package com.example.notification_consumer.service.Implementation;

import com.example.notification_consumer.dto.DeleteResponseDto;
import com.example.notification_consumer.dto.NotificationDto;
import com.example.notification_consumer.exception.NotFoundException;
import com.example.notification_consumer.mapper.NotificationMapper;
import com.example.notification_consumer.model.Notification;
import com.example.notification_consumer.model.NotificationType;
import com.example.notification_consumer.repository.NotificationRepository;
import com.example.notification_consumer.service.NotificationService;
import com.example.notification_consumer.service.NotificationTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImplementation implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final NotificationTypeService notificationTypeService;

    @Override
    public NotificationDto create(NotificationDto notificationDto) {
        Notification notification = notificationMapper.toEntity(notificationDto);
        NotificationType notificationType = notificationTypeService.findById(notificationDto.getTypeId());
        Notification savedNotification = notificationRepository.save(notification);
        return notificationMapper.toDto(savedNotification);
    }

    @Override
    public NotificationDto update(NotificationDto notificationDto) {
        Notification notification = findById(notificationDto.getId());
        NotificationType notificationType = notificationTypeService.findById(notificationDto.getTypeId());
        notificationMapper.update(notificationDto, notification);
        notification.setType(notificationType);
        return notificationMapper.toDto(notificationRepository.save(notification));
    }

    @Override
    public NotificationDto find(Long id) {
        Notification notification = findById(id);
        return notificationMapper.toDto(notification);
    }

    @Override
    public Notification findById(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Notification not found with id: " + id));
    }

    @Override
    public DeleteResponseDto delete(Long id) {
        Notification notification = findById(id);
        notificationRepository.delete(notification);
        return new DeleteResponseDto(id, "Notification deleted successfully");
    }

    @Override
    public Page<NotificationDto> findAll(int page, int size, String sortBy, String sortDirection) {
        Page<Notification> notifications = notificationRepository.findAll(
                PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy))
        );
        return notifications.map(notificationMapper::toDto);
    }
}
